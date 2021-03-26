<template>
  <el-dialog
    title="operation"
    :visible="dialogVisible"
    :close-on-click-modal="false"
    width="30%"
    append-to-body
    :before-close="handleClose"
  >
    <el-form v-if="oldRow" ref="dialogForm" :model="form" label-width="100px"
    style="width:90%" :rules="rules">
      <el-form-item label="id" prop="id">
        <el-input v-model="form.id" disabled/>
      </el-form-item>
      <el-form-item label="name" prop="name">
        <el-input v-model="form.name" disabled />
      </el-form-item>
      <el-form-item label="age" prop="age">
        <el-input v-model="form.age" />
      </el-form-item>
    </el-form>

    <el-form v-else ref="dialogForm" :model="form" label-width="100px"
    style="width:90%" :rules="rules">
      <el-form-item label="name" prop="name">
        <el-input v-model="form.name"/>
      </el-form-item>
      <el-form-item label="age" prop="age">
        <el-input v-model="form.age" />
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button size="small" type="danger" @click="close">cancel</el-button>
    <el-button size="small" type="primary" :loading="loading"
    @click="confirm('dialogForm')">confirm</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { addPerson, updatePerson } from '@/api/person';

const defaultForm = () => ({
  id: '',
  name: '',
  age: '',

});
export default {
  name: 'PersonDialog',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    oldRow: {
      type: Object,
      default: () => ({}),
    },
  },
  data() {
    const validatePass1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Name cannot be empty'));
      } else {
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Age cannot be empty'));
      } else {
        callback();
      }
    };
    return {
      dialogVisible: this.visible,
      loading: false,
      isAdd: false,
      form: defaultForm(),
      rules: {
        name: [{ validator: validatePass1, trigger: 'blur' }],
        age: [{ validator: validatePass2, trigger: 'blur' }],
      },
    };
  },
  watch: {
    visible: {
      handler(show) {
        this.dialogVisible = show;
        if (show) {
          this.init();
        }
      },
    },
  },
  methods: {
    init() {
      // 新增
      if (!this.oldRow) {
        this.form = defaultForm();
        return;
      }
      // 修改
      this.form.id = this.oldRow.id;
      this.form.name = this.oldRow.name;
      this.form.age = this.oldRow.age;
    },
    close() {
      // 向index父组件中传值
      this.$emit('update', this.visible);
      this.dialogVisible = false;
    },
    handleClose(done) {
      this.close();
      done();
    },
    confirm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.loading = true;
          try {
            let res = {};
            this.loading = true;
            // 新增时候
            if (!this.oldRow) {
              res = await addPerson(this.form);
            } else { // 修改时候
              res = await updatePerson(this.form);
            }

            const { code } = res;

            if (code === 200) {
              this.$layer.msg('operate successfully', { type: 'success' });
              this.close();
              this.$emit('init');
            }
          } finally {
            this.loading = false;
          }
        }
      });
    },
  },
};
</script>

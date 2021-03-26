<template>
<div v-loading="loading" class="app-container ml-10" style="height: 600px; border: 1px solid #eee">
        <div class="search-container">
          <div class="flex-inline mt-20 ml-20">
        <el-input class="mr-20" v-model="form.name"
         placeholder="input name" style="width:200px" @keyup.enter.native="query()"/>
        <el-input class="mr-20" v-model ="form.age" placeholder="input age" style="width:200px"
        @keyup.enter.native="query()" />
        <el-button size="small" type="success" @click="query">search</el-button>
          <el-button size="small" type="success" @click="add">add person</el-button>
       </div>
    </div>
 <div class="flex-inline">
  <el-table
      :data="tableData"
      border
      class="mt-20"
    >
      <el-table-column align="center" prop="id" label="id" width="150" />
      <el-table-column align="center" prop="name" label="name" />
      <el-table-column align="center" prop="age" label="age" />
      <el-table-column align="center" prop="" label="option" width="180">
        <template v-slot="{row}">
          <el-button type="success" size="mini"  @click="edit(row)">update</el-button>
          <el-button type="danger" size="mini"  @click="del(row)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>
 </div>
    <person-dialog :old-row="oldRow" :visible="visible" @update="update()" @init="init" />
</div>
</template>

<style>
</style>

<script>
// eslint-disable-next-line import/named
import { getPerson, deletePerson } from '@/api/person';
// eslint-disable-next-line import/extensions
import PersonDialog from './components/PersonDialog';
// eslint-disable-next-line import/extensions

const defaultForm = () => ({
  name: '',
  age: '',
});

export default {
  name: 'ShowAll',
  components: { PersonDialog },
  data() {
    return {
      tableData: [],
      visible: false,
      loading: false,
      oldRow: {},
      form: defaultForm(),
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.query();
    },
    async query() {
      try {
        this.loading = true;
        // eslint-disable-next-line no-console
        console.log(this.form);
        const { code, list } = await getPerson(this.form);
        if (code === 200) {
          this.tableData = Array.isArray(list) ? list : [];
        }
      } finally {
        this.loading = false;
      }
    },
    // 向子组件dialog传递两个参数, add添加中oldrow是空行
    add() {
      this.visible = true;
      this.oldRow = null;
    },
    // edit中oldrow中本来就有数据,将该行row赋值给oldrow
    edit(row) {
      this.visible = true;
      this.oldRow = row;
    },
    // 删除
    del({ id }) {
      this.$layer.confirm('Are you sure you want to delete it?').then(async () => {
        const { code } = await deletePerson({ id });
        if (code === 200) {
          this.$layer.msg('Delete person has succeed', { type: 'success' });
          this.query();
        }
      });
    },
    update(formstate) {
      this.visible = formstate;
    },
  },
};
</script>

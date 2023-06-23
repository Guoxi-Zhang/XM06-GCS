<template>
  <div class="app-container">

    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-col :span="8">
        <el-form-item label="申请编号" prop="tableId" >
          {{formData.tableId}}
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="学号" prop="studentId">
          {{formData.studentId}}
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="学生姓名" prop="studentName">
          {{formData.studentName}}
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="学院" prop="school">
          {{formData.school}}
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="年级" prop="grade">
          {{formData.grade}}
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="爱心礼包ID" prop="packId">
          {{formData.packId}}
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="审核单位" prop="nowStep">
          <dict-tag :options="dict.type.verify_unit" :value="formData.nowStep"/>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="审核状态" prop="applyState">
          <dict-tag :options="dict.type.verify_state" :value="formData.applyState"/>
        </el-form-item>
      </el-col>
    </el-form>


    <el-table v-loading="loading" :data="verifyList" @selection-change="handleSelectionChange">
      <el-table-column label="审核编号" align="center" prop="verifyId" />
      <el-table-column label="审核单位" align="center" prop="verifyUnit" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_unit" :value="scope.row.verifyUnit"/>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="verifyPersonName" />
      <el-table-column label="审核操作" align="center" prop="verifyAction" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_action" :value="scope.row.verifyAction"/>
        </template>
      </el-table-column>
      <el-table-column label="审核意见" align="center" prop="verifyAdvice" />
    </el-table>

    <el-row :gutter="10" class="mb8" v-if="testVerifible() && !this.$auth.hasRole('student')">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >审核</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审核操作" prop="verifyAction">
          <el-radio-group v-model="form.verifyAction">
            <el-radio
              v-for="dict in dict.type.verify_action"
              :key="dict.value"
              :label="dict.value"
              v-if="dict.value != 0"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核意见" prop="verifyAdvice">
          <el-input v-model="form.verifyAdvice" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addVerify">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getVerifyListByApplyId, modifyPackApply, addPackVerify } from "@/api/verify/pack_list";

export default {
  name: "Dict",
  dicts: ['sys_normal_disable', 'verify_action', 'verify_state', 'verify_unit'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 字典表格数据
      verifyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        applyId: this.$route.params.applyId
      },
      formData: {
        tableId: undefined,
        studentId: undefined,
        studentName: undefined,
        school: undefined,
        grade: undefined,
        packId: undefined,
        nowStep: undefined,
        applyState: undefined,
      },
      rules: {
        dictName: [
          { required: true, message: "字典名称不能为空", trigger: "blur" }
        ],
        dictType: [
          { required: true, message: "字典类型不能为空", trigger: "blur" }
        ]
      },
      form: {
        verifyAction: null,
        verifyAdvice: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询字典类型列表 */
    getList() {
      this.loading = true;
      console.log("getVerifyListByApplyId")
      getVerifyListByApplyId(this.queryParams).then(response => {
          this.total = response.total;
          this.loading = false;
          if (response.total > 0) {
            this.formData.tableId = response.rows[0].tableId
            this.formData.studentId = response.rows[0].studentId
            this.formData.studentName = response.rows[0].studentName
            this.formData.school = response.rows[0].school
            this.formData.grade = response.rows[0].grade
            this.formData.packId = response.rows[0].packId
            this.formData.nowStep = response.rows[0].nowStep
            this.formData.applyState = response.rows[0].applyState;
            this.verifyList = response.rows[0].verifyHistories;
          }
        }
      );
    },
    // 取消按钮
    cancel() {
      this.open = false;
    },
    // 表单重置
    reset() {
      this.form = {
        dictId: undefined,
        dictName: undefined,
        dictType: undefined,
        status: "0",
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.open = true;
      this.title = "申请审核";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.dictId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dictId = row.dictId || this.ids
      getType(dictId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改字典类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log("no submit")
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const dictIds = row.dictId || this.ids;
      this.$modal.confirm('是否确认删除字典编号为"' + dictIds + '"的数据项？').then(function() {
        return delType(dictIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/dict/type/export', {
        ...this.queryParams
      }, `type_${new Date().getTime()}.xlsx`)
    },
    /** 刷新缓存按钮操作 */
    handleRefreshCache() {
      refreshCache().then(() => {
        this.$modal.msgSuccess("刷新成功");
        this.$store.dispatch('dict/cleanDict');
      });
    },
    testVerifible() {
      if (this.$auth.hasRole("admin")) {
        return true;
      } else if (this.$auth.hasRole("student")) {
        return this.formData.applyState == 2;
      } else if (this.$auth.hasRole("counselor")) {
        return this.formData.nowStep == 0 && this.formData.applyState == 0;
      } else if (this.$auth.hasRole("school")) {
        return this.formData.nowStep == 1 && this.formData.applyState == 0;
      } else if (this.$auth.hasRole("university")) {
        return this.formData.nowStep == 2
          && this.formData.applyState != 1 && this.formData.applyState != 2;
      } else {
        return false;
      }
    },
    addVerify() {
      let query = {
        applyId: this.formData.tableId,
        verifyAction: this.form.verifyAction,
        verifyAdvice: this.form.verifyAdvice
      };
      addPackVerify(query);
      this.open = false;
      location.reload()
    }
  }
};
</script>

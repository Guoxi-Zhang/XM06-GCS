<template>
  <div class="app-container">

    <span style="color:red" v-if="this.$auth.hasRole('admin')"> admin无法在此页面进行审核，请使用其他角色账号登陆,或前往审核管理页面进行管理</span>

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请表ID" prop="applyId">
        <el-input
          v-model="queryParams.tableId"
          placeholder="请输入申请表ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生学号" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学院" prop="school">
        <el-input
          v-model="queryParams.school"
          placeholder="请输入学院"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年级" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入年级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="arear_applyList" @selection-change="handleSelectionChange">
      <!--      <el-table-column type="selection" width="55" align="center" :selectable="checkSelect"/>-->
      <el-table-column label="申请编号" align="center" prop="tableId" />
      <el-table-column label="申请批次" align="center" prop="batchId" />
      <el-table-column label="学生学号" align="center" prop="studentId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="学院" align="center" prop="school" />
      <el-table-column label="年级" align="center" prop="grade" />
      <el-table-column label="欠费项目" align="center" prop="arearId" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.arrear_type" :value="scope.row.arearId"/>
        </template>
      </el-table-column>
      <el-table-column label="应缴金额" align="center" prop="arearCost" />
      <el-table-column label="缓缴金额" align="center" prop="arearAmount" />
      <el-table-column label="缓缴原因" align="center" prop="arearReason" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.arrear_reason" :value="scope.row.arearReason"/>
        </template>
      </el-table-column>
      <el-table-column label="待审核单位" align="center" prop="nowStep">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_unit" :value="scope.row.nowStep"/>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="applyState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.verify_state" :value="scope.row.applyState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
          >
            <router-link :to="'/verify/arear_list/verify_list/' + scope.row.tableId">
              <span>查看</span>
            </router-link>
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { getArearVerifyList } from "@/api/verify/arear_list";

export default {
  name: "Arear_Verify",
  dicts: ['apply_type', 'sys_yes_no', 'verify_action', 'verify_unit', 'verify_state', 'arrear_type',
    'arrear_reason'],
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
      // 审核历史表格数据
      arear_applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tableId: null,
        studentId: null,
        school: null,
        grade: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applyId: [
          { required: true, message: "审核的申请表ID", trigger: "blur" }
        ],
        applyType: [
          { required: true, message: "申请类型", trigger: "change" }
        ],
        verifyPersonId: [
          { required: true, message: "审核人员ID", trigger: "blur" }
        ],
        verifyAction: [
          { required: true, message: "审核操作", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询审核历史列表 */
    getList() {
      this.loading = true;
      getArearVerifyList(this.queryParams).then(response => {
        this.arear_applyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        verifyId: null,
        applyId: null,
        applyType: null,
        verifyPersonId: null,
        verifyAction: null,
        verifyAdvice: null,
        isDeleted: null,
        createTime: null,
        updateTime: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加审核历史";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const verifyId = row.verifyId || this.ids
      getVerify_history(verifyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审核历史";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.verifyId != null) {
            updateVerify_history(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVerify_history(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const verifyIds = row.verifyId || this.ids;
      this.$modal.confirm('是否确认删除审核历史编号为"' + verifyIds + '"的数据项？').then(function() {
        return delVerify_history(verifyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('verify/verify_history/export', {
        ...this.queryParams
      }, `verify_history_${new Date().getTime()}.xlsx`)
    },

    checkSelect(row) {
      let step = row.nowStep;
      let state = row.applyState;
      if (this.$auth.hasRole("admin")) { // student
        return true;
      } else if (this.$auth.hasRole("counselor")) { // conselor
        return step == 0 && state == 0;
      } else if (this.$auth.hasRole("school")) { // school
        return step == 1 && state == 0;
      } else if (this.$auth.hasRole("university")) { // university
        return step == 2 && state == 0;
      } else {
        return false;
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.tableId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handlePass() {
      let query = {
        applyIds: this.ids
      }
      console.log(query)
      addBenefitVerifyByApplyIds(query);
    }
  }
};
</script>

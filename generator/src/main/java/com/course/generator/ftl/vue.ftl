<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit red2"></i>
                Add
            </button>
            &nbsp;
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh red2"></i>
                Refresh
            </button>
        </p>

        <pagination ref="pagination" v-bind:list="list"></pagination>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <#list fieldList as field>
                <th>${field.nameCn}</th>
            </#list>
            <th>Operation</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="${domain} in ${domain}s">
                <#list fieldList as field>
                <td>{{${domain}.${field.nameHump}}}</td>
            </#list>

            <td>
                <div class="hidden-sm hidden-xs btn-group">

                    <button v-on:click="edit(${domain})" class="btn btn-xs btn-info">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button v-on:click="del(${domain}.id)" class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                </div>

                <div class="hidden-md hidden-lg">
                    <div class="inline pos-rel">
                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                data-position="auto">
                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                        </button>

                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                            <li>
                                <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </td>
            </tr>
            </tbody>
        </table>

        <div id="form-modal" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Form</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <#list fieldList as field>
                            <div class="form-group">
                                <label>${field.nameCn}</label>
                                <input v-model="${domain}.${field.nameHump}" type="text" class="form-control"
                                       aria-describedby="emailHelp">
                            </div>
                        </#list>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button v-on:click="save()" type="button" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        name: '${domain}',
        components: {Pagination},
        data: function () {
            return {
            ${domain}:
            {
            }
        ,
            ${domain}s: []
        }
        },
        mounted() {
            //this.$parent.activeSidebar("${module}-${domain}-sidebar");
            let _this = this;
            _this.list(1);
        },
        methods: {
            add() {
                let _this = this;
                _this.${domain} = {};
                $("#form-modal").modal("show");
            },

            edit(${domain}) {
                let _this = this;
                _this.${domain} = $.extend({}, ${domain});
                $("#form-modal").modal("show");
            },

            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                })
                    .then((response) => {
                        Loading.hide();
                        //console.log("${Domain} query result: ", response);
                        let resp = response.data;
                        _this.${domain}s = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
                    })
            },

            save() {
                let _this = this;

                // Validation

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/save', _this.${domain})
            .then((response) => {
                    Loading.hide();
                    //console.log("Save ${domain} result: ", response);
                    let resp = response.data;
                    if (resp.success) {
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        Toast.success("Save success");
                    } else {
                        Toast.warning(resp.message);
                    }
                })
            },

            del(id) {
                let _this = this;
                Confirm.show("You cannot revert delete, are you sure?", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/delete/' + id)
                        .then((response) => {
                            Loading.hide();
                            //console.log("Delete ${domain} result: ", response);
                            let resp = response.data;
                            if (resp.success) {
                                _this.list(1);
                                Swal.fire(
                                    'Deleted!',
                                    'The course has been deleted.',
                                    'success'
                                )
                            }
                        })
                });

            },
        }
    }
</script>
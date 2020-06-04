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
                <th>ID</th>
                <th>Name</th>
                <th>Course ID</th>
                <th>Operation</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>
                <td>{{chapter.courseId}}</td>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">

                        <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
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
                            <div class="form-group">
                                <label>Name</label>
                                <input v-model="chapter.name" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <label>Course ID</label>
                                <input v-model="chapter.courseId" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Course ID">
                            </div>
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
        name: 'chapter',
        components: {Pagination},
        data: function () {
            return {
                chapter: {},
                chapters: []
            }
        },
        mounted() {
            //this.$parent.activeSidebar("business-chapter-sidebar");
            let _this = this;
            _this.list(1);
        },
        methods: {
            add() {
                let _this = this;
                _this.chapter = {};
                $("#form-modal").modal("show");
            },

            edit(chapter) {
                let _this = this;
                _this.chapter = $.extend({}, chapter);
                $("#form-modal").modal("show");
            },

            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                })
                    .then((response) => {
                        Loading.hide();
                        console.log("Chapter query result: ", response);
                        let resp = response.data;
                        _this.chapters = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
                    })
            },

            save() {
                let _this = this;

                // Validation
                if (!Validator.require(_this.chapter.name, "name") ||
                    !Validator.require(_this.chapter.courseId, "Course ID") ||
                    !Validator.length(_this.chapter.courseId, "Course ID", 1, 8)) {
                    return;
                }

                Loading.show();
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save', _this.chapter)
                    .then((response) => {
                        Loading.hide();
                        console.log("Save chapter result: ", response);
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
                    _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id)
                        .then((response) => {
                            Loading.hide();
                            console.log("Delete chapter result: ", response);
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
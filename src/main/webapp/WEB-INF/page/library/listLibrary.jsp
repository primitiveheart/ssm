<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/7
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ztree demo 示例</title>

    <link rel="stylesheet" type="text/css" href="../resources/css/ztree/zTreeStyle/zTreeStyle.css">

    <script src="../resources/js/ztree/jquery-1.4.4.min.js"></script>
    <script src="../resources/js/ztree/jquery.ztree.core-3.5.min.js"></script>
    <script src="../resources/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="../resources/js/ztree/jquery.ztree.exedit-3.5.min.js"></script>

    <script type="text/javascript">
       var dragId;
       var zTree_Menu;
       var native_name;
        var setting = {
            view: {
                    addHoverDom: addHoverDom,
                    removeHoverDom: removeHoverDom,
                    selectedMulti: false,
                    showLine: true,
                    showIcon: true
            },
            edit: {
                enable: true,
                showRemoveBtn: setRemoveBtn,
                removeTitle:"删除分类",
                renameTitle:"编辑分类",
                drag: {
                    prev: true,
                    next: true,
                    inner: false
                },
                editNameSelectAll: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                beforeDrag: beforeDrag,
                beforeDrop: beforeDrop,
                beforeEditName: beforeEditName,
                beforeRemove: beforeRemove,
                beforeRename: beforeRename,
                onRemove: onRemove,
                onRename: onRename
            }
        };

       function beforeClick(treeid, treeNode, clickFlag){
           return false;
       }

       function beforeDrag(treeId, treeNodes) {//拖拽时执行
           for (var i=0,l=treeNodes.length; i<l; i++) {
               dragId = treeNodes[i].pId;
               if (treeNodes[i].drag === false) {
                   return false;
               }
           }
           return true;
       }

       //拖拽释放后执行
       function beforeDrop(treeId, treeNodes, targetNode, moveType) {
           if(targetNode.pId == dragId){
               var data = {id:treeNodes[0].id,targetId:targetNode.id,moveType:moveType};
               var confirmVal = false;
               $.ajax({
                   async:false,
                   type:"post",
                   data:data,
                   url:"updateLibrarySort",
                   success:function (json) {
                       if(json == "success"){
                           confirmVal = true;
                           alert("操作成功");
                       }else{
                           alert("操作失败");
                       }
                   },
                   error:function () {
                       alert("网络有点不给力");
                   }
               })
               return confirmVal;
           }else{
               alert("只能进行同级排序");
               return false;
           }
       }


       function beforeEditName(treeId, treeNode) {//用于捕获节点编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态
           var zTree = $.fn.zTree.getZTreeObj("treeDemo");
           zTree.selectNode(treeNode);
           return true;
       }
       //删除之前
       function beforeRemove(treeId, treeNode) {//用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
           var zTree = $.fn.zTree.getZTreeObj("treeDemo");
           zTree.selectNode(treeNode);
           var confirmFlag = confirm("确认删除 分类 -- " + treeNode.name + " 吗？");
           var confirmVal = false;
           if(confirmFlag){
               var data = {id:treeNode.id};
               $.ajax({
                   async:false,
                   type:"post",
                   data:data,
                   url:"deleteLibrary",
                   success:function (json) {
                       if(json == "success"){
                           confirmVal = true;
                       }else{
                           alert("删除失败");
                       }
                   },
                   error:function () {
                       alert("删除失败");
                   }
               })
           }
           return confirmVal;
       }
       //删除之后
       function onRemove(e, treeId, treeNode) {//用于捕获删除节点之后的事件回调函数
           alert("删除成功");
       }

       //重命名之前
       function beforeRename(treeId, treeNode, newName) {//更新节点名称数据之前的事件回调函数，并且根据返回值确定是否允许更改名称的操作
           if (newName.length == 0 || newName.indexOf("请输入名称")>=0) {
               alert("分类名称不能为空.");
               var zTree = $.fn.zTree.getZTreeObj("treeDemo");
               setTimeout(function(){zTree.editName(treeNode)}, 10);
               return false;
           }
           if(newName.length > 15){
               alert("分类名称过长");
               var zTree = $.fn.zTree.getZTreeObj("treeDemo");
               setTimeout(function () {
                   zTree.editName(treeNode)
               }, 10);
               return false;
           }
           native_name = treeNode.name;
           return true;
       }
       //重命名之后
       function onRename(e, treeId, treeNode) {//用于捕获节点编辑名称结束之后的事件回调函数
            if(native_name == treeNode.name){
                return;
            }
            var data ={id:treeNode.id,level_id:treeNode.level,pid:treeNode.pId,name:treeNode.name}
            $.ajax({
                async:false,
                type:"post",
                data:data,
                url:"updateLibraryName",
                success:function (json) {
                    if(json == "success"){
                        alert("操作成功");
                    }else{
                        alert("操作失败，请稍后再试");
                    }
                },
                error:function () {
                    alert("网络有点不给力");
                }
            })
       }

       //增加分类
       function addHoverDom(treeId, treeNode) {//用于当鼠标移动到节点上时，显示用户自定义控件
           var sObj = $("#" + treeNode.tId + "_span");
           if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0 || treeNode.level == 3) return;
           var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                   + "' title='添加分类' onfocus='this.blur();'></span>";
           sObj.after(addStr);
           var btn = $("#addBtn_"+treeNode.tId);
           if (btn) btn.bind("click", function(){
               var zTree = $.fn.zTree.getZTreeObj("treeDemo");
               var treeNodes;
               $.ajax({
                   async:false,
                   type:"post",
                   url:"saveLibrary",
                   success:function (libraryId) {
                       if(libraryId != ""){
                           treeNodes = zTree.addNodes(treeNode,{id:
                                   (libraryId),pid:treeNode.id,name:"请输入名称"});
                       }
                       if(treeNodes){
                           zTree.editName(treeNodes[0]);
                       }
                   },
                   error:function () {
                       alert("网络有点不给力");
                   }
               })
               return false;
           });
       }
       //父分类去掉删除功能
       function setRemoveBtn(treeId, treeNode) {
           return !treeNode.isParent;
       }

       function removeHoverDom(treeId, treeNode) {
           $("#addBtn_"+treeNode.tId).unbind().remove();
       };

       function selectAll() {
           var zTree = $.fn.zTree.getZTreeObj("treeDemo");
           zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
       };

       function remove(e) {
           var zTree = $.fn.zTree.getZTreeObj("treeDemo");
           nodes = zTree.getSelectedNodes();
           treeNode = nodes[0];
           if(nodes.length == 0){
               alert("请您选择一个分类");
               return;
           }
           var callbackFlag = $("#callbackTrigger").attr("checked");
           zTree.removeNode(treeNode, callbackFlag);
       }

       //展开全部分类
       function expandAllFlag() {
           zTree_Menu.expandAll(true)
       }

       //合并全部分类
       function combineAllFlag() {
           zTree_Menu.expandAll(false);
       }

       function onloadZTree() {
           var ztreeNodes;
           $.ajax({
               async:true,
               cache:false,
               type:'post',
               dataType:"json",
               url:"findAllLibrary",
               error:function () {
                   alert("网络有点不给力");
               },
               success:function (data) {
                   //将string字符串转换成接送对象
                   ztreeNodes = eval("[" + data+"]");
                   $.fn.zTree.init($("#treeDemo"), setting, ztreeNodes);
                   zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
                   $("#selectAll").bind("click", selectAll);
                   expandAllFlag();
               }
           })
       }

       $(document).ready(function(){
           onloadZTree();
       });

    </script>

</head>
<body>
    <div class="widget-box">
       <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
            <h5>分类管理</h5>
       </div>
        <div class="widget-content tab-content" >
            <!--分类管理开始-->
            <div class="content_wrap" >
                <div class="zTreeDemoBackground ">
                    <ul id="treeDemo" class="ztree"></ul>
               </div>
            </div>
               <!--分类管理结束-->
       </div>
    </div>

</body>
</html>

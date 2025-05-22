$(document).ready(() => {
    $.ajax({
        url: "../Controllers/ApiRest.php",
        Type: "GET",
        dataType: "json",
        success: (data) => $("#dg").datagrid('loadData',data)
    })
} );

$(document).ready(function() {
    $("#fm").submit(function(event) {
    var formData=$(this).serialize();
    var formulario=this;
    $.ajax({
        url:'../Controllers/ApiRest.php',
        type:'POST',
        dataType:'json',
        success:function(response){
        alert("Estudiante guardado");
        formulario.reset();
        $('$dg').datagrid('reload');
        },
        error:function(xhr, status, error){
        alert("Error al guardar el estudiante: " + error);
        }
    })
})
})
function updateState(){
    $.get("game", function (result) {
        console.log(result);
        if(("" + document.getElementById("result").innerHTML) != ("" + result))
            document.getElementById("result").innerHTML = result;
    })
}

$(document).ready(function () {

    setInterval(function(){ updateState(); }, 3000);

})


function takeCell(n,m) {
    $.post("game",{line:n,column:m},function () {
        console.log("Am postat :))")
        updateState();

    })

}

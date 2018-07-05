let averages =[];
let nrWords;
let nrLettersFromWords=0;
function orderParagraphsDecreasingByAverageNumbOfCharacters(){
    var arrOfPtags = document.getElementsByTagName("p");
    for (var i = 0;i < arrOfPtags.length; i++){
        console.log(arrOfPtags[i].innerHTML);
        var nrWords = arrOfPtags[i].innerHTML.split(' ').length;
        var j;
        var count = 0;
        for(j=0;j<arrOfPtags[i].innerHTML.length;j++){
            if (arrOfPtags[i].innerHTML.charAt(j) !== ' '){
                count = count + 1;
            }
        }
        averages.push({
            key : arrOfPtags[i],
            value : count/nrWords});
    }
    console.log(averages);
    averages.sort(function(first, second) {
        return second.value - first.value;
    });
    console.log(averages);
    //$("body").html("");
    $("p").remove();
    for (item in averages){
        console.log(item);
        $("body").append("<p>");
        $("body").append(averages[item].key.innerHTML);
        $("body").append("</p>");

    }
}

function printParagraphs(){}

$( document ).ready(function() {
    orderParagraphsDecreasingByAverageNumbOfCharacters();
})
function addCatEvaluation(description){

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/BookRating_war_exploded/Livres/AddLivreLu/"+login+"/"+idLivre, true);
    xhr.send();


}
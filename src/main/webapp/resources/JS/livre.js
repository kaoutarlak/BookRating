function addLivreLu(login,idLivre){
    // $.ajax({
    //     url: "/BookRating_war_exploded/Livres/AddLivreLu/"+login+"/"+idLivre,
    //     type: "GET",
    //     success: function(data) {
    //         alert("Livre bien ajouté à la liste des livres déjà lus.");
    //     }
    // });
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/BookRating_war_exploded/Livres/AddLivreLu/"+login+"/"+idLivre, true);
    xhr.send();
    alert("Livre bien ajouté à la liste des livres déjà lus.");

}
$(document).ready(function () {

    //inital amount in the machine
    var totalAmount = 0;

    $("#totalAmount").val("$0.00");

    //function to add 1 Dollar-- multiplied*100 to avoid confusion with decimals, but divide by 100 when being send or shown on screen
    $("#addDollar").on("click", function () {
        calculate(totalAmount += 100);//change the input to the new amount that was added
        $("#change").val("");//set the change to zero if the above button is clicked

    })

    //function to add 0.25 Dollar-- multiplied*100 to avoid confusion with decimals
    $("#addQuarter").on("click", function () {
        calculate(totalAmount += 25);//change the input to the new amount that was added
        $("#change").val("");//set the change to zero if the above button is clicked

    })

    //function to add 0.10 Dollar-- multiplied*100 to avoid confusion with decimals
    $("#addDime").on("click", function () {
        calculate(totalAmount += 10);
        $("#change").val("");

    })

    //function to add 0.05 Dollar-- multiplied*100 to avoid confusion with decimals
    $("#addNickel").on("click", function () {
        calculate(totalAmount += 05);
        $("#change").val("");
    })

    //get request was wrapped into a funcion to show updated amount of items when item is purchased by you
    function refresh() {
        $("#allItems").empty();
        $.ajax({
            type: "GET",
            //url: "http://tsg-vending.herokuapp.com/items",
            url: "http://localhost:8080/api/items",
            success: function (allItems) {

                //loops through every item in the VendingMachine to add it to a card
                $.each(allItems, function (i, item) {
                    var card = $("<div class='card mx-auto' style='width:14rem; height:12rem; margin: 10px 0px '></div>");
                    var cardBody = $("<div class='card-body font-weight-bold text-center ' style='color:indigo; border:groove;'>");
                    var number = i + 1;
                    cardBody.append("<div class='card-text text-left '>" + number + "</div>");
                    var name = item.name;
                    cardBody.append("<div class='card-text'>" + name + "</div>");
                    var price = item.price;
                    cardBody.append("<div class='card-text'> $" + price + "</div>");
                    var quantity = item.quantity;
                    cardBody.append("<div class='card-footer bg-transparent'> Quantity Left: " + quantity + "</div>");
                    card.append(cardBody);
                    $("#allItems").append(card);

                    //adds a click event to every card
                    card.on("click", function () {
                        $("#item").val(number);
                        $('#makePurchase').off("click");
                        $('#makePurchase').on("click", function () {
                            $("#change").val("");
                            $.ajax({
                                type: "PUT",
                                url: "http://localhost:8080/api/money/" + totalAmount / 100 + "/item/" + item.id,
                                success: function (change) {
                                    //alert("success");
                                    refresh();
                                    $("#messages").empty();
                                    $("#messages").val("Thank you!");
                                    $("#totalAmount").val("$0.00");
                                    $("#change").val(convertChangeToString(change));
                                    totalAmount = 0;
                                },
                                error: function (jqXHR, textStatus, errorThrown) {

                                    $("#messages").empty();
                                    $("#messages").val(jqXHR.responseJSON.message);

                                }

                            });
                        });
                    });
                });

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("FAILURE!");
            }
        });
    }

    refresh();
    $("#returnChange").on("click", function () {
        $("#totalAmount").val("$0.00");
        $("#messages").val("");
        $("#item").val("");
        $("#change").val("");

        if (totalAmount !== 0) {
            var allTheChange = convertChangeToString(calculateChange(totalAmount));
            $("#change").val(allTheChange);
        }
        totalAmount = 0;
    });


});

//add the money to total Amount
function calculate(amount) {
    $("#totalAmount").val("$" + (amount / 100).toFixed(2));
}

function calculateChange(totalAmount) {
    var totalChange = totalAmount;
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;
    var pennies = 0;
    while (totalChange > 0) {
        if (totalChange >= 25) {
            totalChange -= 25;
            quarters++;
        }
        else if (totalChange >= 10) {
            totalChange -= 10;
            dimes++;
        }
        else if (totalChange >= 05) {
            totalChange -= 05;
            nickels++;
        }
        else {
            totalChange -= 01;
            pennies++;
        }
    }

    return {
        "quarters": quarters,
        "dimes": dimes,
        "nickels": nickels,
        "pennies": pennies
    }



}

function convertChangeToString(amount) {
    var changeString = "";
    if (amount.quarters !== 0) {
        changeString += amount.quarters + "Quarter(s) ";
    }
    if (amount.dimes !== 0) {
        changeString += amount.dimes + "Dime(s) ";
    }
    if (amount.nickels !== 0) {
        changeString += amount.nickels + "Nickel(s) ";
    }
    if (amount.pennies !== 0) {
        changeString += amount.pennies + "Pennie(s)";
    }

    return changeString;
}


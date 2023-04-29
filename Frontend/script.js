// need to add required feature for name, price, and amount when + and -

// indicate current number of items
let unitTypeLst = [];
let massUnitsLst = [];
let volumeUnitsLst = [];
let lengthUnitsLst = [];
let categoryLst = [];

let itemCount = 2;

setUpListsAndDB();
setUpBtns();

// load unitType, unit, and the category from the server 
async function setUpListsAndDB() {
    await genCategoryList();

    let categoryDropdown1 = document.getElementById("category-dropdown-1");
    let categoryDropdown2 = document.getElementById("category-dropdown-2");
    genCategoryDropdown(categoryDropdown1);
    genCategoryDropdown(categoryDropdown2);

    await genUnitLists();

    let unitTypeDropdown1 = document.getElementById("unit-type-dropdown-1");
    let unitDropdown1 = document.getElementById("unit-dropdown-1");

    let unitTypeDropdown2 = document.getElementById("unit-type-dropdown-2");
    let unitDropdown2 = document.getElementById("unit-dropdown-2");

    genUnitDropdown(unitTypeDropdown1, unitDropdown1);
    genUnitDropdown(unitTypeDropdown2, unitDropdown2);

    unitTypeDropdown1.addEventListener("change", () => genUnitDropdown(unitTypeDropdown1, unitDropdown1));
    unitTypeDropdown2.addEventListener("change", () => genUnitDropdown(unitTypeDropdown2, unitDropdown2));

    let itemArr = await getAllItems();
    createTable(itemArr);
}

// add events to the buttons
function setUpBtns() {
    let compareBtn = document.getElementById("compare-button");
    let addItemBtn = document.getElementById("add-item-button");
    let removeItemBtn = document.getElementById("remove-item-button");
    let searchItemBtn = document.getElementById("search-item-button");

    compareBtn.addEventListener("click", () => {
        setUpCompareBtn();
    });

    
    addItemBtn.addEventListener("click", () => {
        itemCount++;

        addNewItem();

        if (itemCount >= 3) {
            removeItemBtn.style.visibility = "visible";
        }
        if (itemCount >= 5) {
            addItemBtn.style.visibility = "hidden";
        }
    })

    removeItemBtn.addEventListener("click", () => {
        removeItem();

        itemCount--;

        if (itemCount < 3) {
            removeItemBtn.style.visibility = "hidden";
        }
        if (itemCount < 5) {
            addItemBtn.style.visibility = "visible";
        }
    })

    searchItemBtn.addEventListener("click", async() => {
        let itemArr = await searchItems();

        clearTable();

        if (itemArr.length > 0) {
            createTable(itemArr);
        }
    })
}

// set up compare button event
async function setUpCompareBtn() {
    let itemArr = document.getElementsByClassName("item");

    if (validateItemArr(itemArr) != false) {
        let itemArrJson = await saveItems(itemArr);
        let updatedItemArr = await getAllItems();

        clearTable();
        createTable(updatedItemArr);

        let comparisionResult = await compareItems(itemArrJson);

        updateCompareResult(comparisionResult);
    } else {
        alert("Field 'Name', 'Amount', and 'Price' must be filled in");
    }
}

// set up add item button event
function addNewItem() {
    let item1Div = document.getElementById("item1");
    let newItemDiv = item1Div.cloneNode(true); // true means clone all childNodes and all event handlers
    newItemDiv.id = `item${itemCount}`;

    let h2AndSelectElem = newItemDiv.querySelectorAll("h2, select");
    for (i = 0; i < h2AndSelectElem.length; i++) {
        let currName = h2AndSelectElem[i].getAttribute("name");

        // change item-index innerHTML
        if (currName == "item-index") {
            h2AndSelectElem[i].textContent = `Item${itemCount}`;
        }
        // change ids
        else if (currName == "unit-type") {
            h2AndSelectElem[i].id = `unit-type-dropdown-${itemCount}`;
        } else if (currName == "unit") {
            h2AndSelectElem[i].id = `unit-dropdown-${itemCount}`;
        } else if (currName == "category") {
            h2AndSelectElem[i].id = `category-dropdown-${itemCount}`
        }
    }

    let formElem = document.getElementById("compare-form");
    formElem.appendChild(newItemDiv);

    let newCategoryDropdown = document.getElementById(`category-dropdown-${itemCount}`);
    genCategoryDropdown(newCategoryDropdown);

    let newUnitTypeDropdown = document.getElementById(`unit-type-dropdown-${itemCount}`);
    let newUnitDropdown = document.getElementById(`unit-dropdown-${itemCount}`);
    genUnitDropdown(newUnitTypeDropdown, newUnitDropdown);

    newUnitTypeDropdown.addEventListener("change", () => genUnitDropdown(newUnitTypeDropdown, newUnitDropdown));
}

// set up remove item button event
function removeItem() {
    let formElem = document.getElementById("compare-form");
    let targetItem = document.getElementById(`item${itemCount}`);

    formElem.removeChild(targetItem);
}

async function searchItems() {
    let text = document.getElementById("search-text").value;

    // push item objs to the itemArray to easily generate the table
    let itemArr = [];

    // user search nothing
    if (text == "") {
        return getAllItems();
    }

    else {
        let response = await fetch(`http://localhost:8081/api/value-comparer/item-search/${text}`);
        let responseJson = await response.json();

        for (let i = 0; i < responseJson.length; i++) {
            itemArr.push(responseJson[i]);
        }
    }

    return itemArr; 
}

/**
 * Generates list of unit types and units
 */
async function genUnitLists() {
    let response = await fetch("http://localhost:8081/api/value-comparer/unit-list");
    let responseJson = await response.json();

    let jsonUnitList = responseJson["unitList"];        // get unitList Map

    let jsonUnitListKeys = Object.keys(jsonUnitList);
    for (let i = 0; i < jsonUnitListKeys.length; i++) { // for each keys in jsonUnitListKeys
        let currKey = jsonUnitListKeys[i];              // get the key ("mass", "volume", "length", and etc)
        unitTypeLst.push(currKey);                      // save the key

        if (currKey.includes("mass")) {
            massUnitsLst.push(...jsonUnitList[currKey]);    // save each items in mass
        } else if (currKey.includes("volume")) {
            volumeUnitsLst.push(...jsonUnitList[currKey]);
        } else if (currKey.includes("length")) {
            lengthUnitsLst.push(...jsonUnitList[currKey]);
        }
    }
}

/**
 * Generates list of categories
 */
async function genCategoryList() {
    let response = await fetch("http://localhost:8081/api/value-comparer/categories");
    let responseJson = await response.json();

    let jsonCategoryList = responseJson["categories"];        // get category 
    let jsonCategoryKeys = Object.keys(jsonCategoryList);

    for (let i = 0; i < jsonCategoryKeys.length; i++) { // for each keys in jsonCategoryKeys
        let currKey = jsonCategoryKeys[i];              // get the key
        categoryLst.push(currKey);                      // save the key
    }
}

/**
 * Generates Category dropdown 
 */
function genCategoryDropdown(categoryDropdown) {
    regenerateChildren("option", categoryLst, categoryDropdown);
}

/**
 * Generates Unit Type dropdown and its corresponding unit dropdown
 */
function genUnitDropdown(unitTypeDropdown, unitDropdown) {
    if (unitTypeDropdown.value == "length") {
        regenerateChildren("option", lengthUnitsLst, unitDropdown);
    } else if (unitTypeDropdown.value == "mass") {
        regenerateChildren("option", massUnitsLst, unitDropdown);
    } else if (unitTypeDropdown.value == "volume") {
        regenerateChildren("option", volumeUnitsLst, unitDropdown);
    }
}

/**
 * Returns a single element with text (e.x., "<option>Option1</option>")
 */
function genElement(elementType, text) {
    let newElement = document.createElement(elementType);
    newElement.innerText = text;
    return newElement;
}

/**
 * Clears parent element of its current children and regenerates new children from elementsLst
 */
function regenerateChildren(elementType, elementsLst, parent) {
    parent.replaceChildren();

    for (let i = 0; i < elementsLst.length; i++) {
        let newText = elementsLst[i];
        let newElement = genElement(elementType, newText);
        parent.appendChild(newElement);
    }
}

// send item info to the backend api and getting back the reflection of the item with the id
async function saveItems(itemArr) {
    let jsonData = itemArrToJson(itemArr);

    let response = await fetch("http://localhost:8081/api/value-comparer/new-items", {
        method: "POST", headers: {
            "Content-Type": "application/json",
        }, body: JSON.stringify(jsonData)
    });
    let itemArrJson = await response.json();

    return itemArrJson;
}

/**
 * need to handle both inputs and selects elements in the targetDiv, based on the name and value, converts to the Json obj and return it.
 */

function itemArrToJson(itemArr) {
    let jsonData = [];

    for (let i = 0; i < itemArr.length; i++) {
        let jsonElem = {};
        let divInputs = itemArr[i].getElementsByTagName('input');
        let divSelects = itemArr[i].getElementsByTagName('select');

        for (let i = 0; i < divInputs.length; i++) {
            // change the value type from string to float if the fileds are amount and price.
            if (divInputs[i].name.includes("amount") || divInputs[i].name.includes("price")) {
                jsonElem[divInputs[i].name] = parseFloat(divInputs[i].value);
            }
            else {
                jsonElem[divInputs[i].name] = divInputs[i].value;
            }
        }

        for (let i = 0; i < divSelects.length; i++) {
            if (divSelects[i].name != "unit-type") {
                jsonElem[divSelects[i].name] = divSelects[i].value;
            }
        }

        jsonData.push(jsonElem);
    }
    return jsonData;
}

function validateItemArr(itemArr) {
    isValid = true; 
    
    for (let i = 0; i < itemArr.length; i++) {
        let divInputs = itemArr[i].getElementsByTagName('input');

        for (let i = 0; i < divInputs.length; i++) {
            if (divInputs[i].name.includes("name") || divInputs[i].name.includes("amount") || divInputs[i].name.includes("price")) {
                if (divInputs[i].value == "") {
                    isValid = false;
                    break; 
                }
            }
        }

        if (isValid == false) {
            break;
        }
    }

    return isValid;
}

// send comparing items' id as a string to the back end and receive the comparison obj that contains the better value item, compared item list, and the value comparision that shows how much the better value item is cheaper than the other items in the list
async function compareItems(itemArrJson) {
    // let itemArrJson = await saveItems(itemArr);
    let idStr = itemArrJsonToIdStr(itemArrJson);

    let response = await fetch(`http://localhost:8081/api/value-comparer/item-comparison/${idStr}`)
    let comparisionResult = await response.json();

    return comparisionResult;
}

// receive item Array list as Json format and covert it to the id string connect with "," (comma)
function itemArrJsonToIdStr(itemArrJson) {
    let idStr = "";

    for (i = 0; i < itemArrJson.length; i++) {
        idStr += itemArrJson[i].id + ",";
    }

    idStr = idStr.substring(0, idStr.length - 1);

    return idStr;
}

// with comparision result from the back end, update the compare-result div content
function updateCompareResult(responseJson) {
    let result = document.getElementById("compare-result");
    // for later: how to change the hardCode to get the betterItem, comparedItemsList, and valueComparision
    let betterItem = responseJson.betterItem;
    let comparedItemsList = responseJson.comparedItemsList;
    // valueComparison shows how much the better item is cheaper than the average
    let valueComparison = responseJson.valueComparison;

    let comparedItemsStr = "";

    for (let i = 0; i < comparedItemsList.length; i++) {
        // exclude the better item from the compared Item List and format it
        if (comparedItemsList[i].name != betterItem.name) {
            comparedItemsStr += comparedItemsList[i].name + ", ";
        }
    }

    // format the item names
    comparedItemsStr = comparedItemsStr.substring(0, comparedItemsStr.length - 2);
    let compareResultStr = genElement("p", `${betterItem.name} is approximately ${Math.floor(valueComparison * 100)}% cheaper than the average of the other items(${comparedItemsStr})`);

    result.replaceChildren();
    result.appendChild(compareResultStr);
}


// receive all items in the db from the back end
async function getAllItems() {
    let response = await fetch("http://localhost:8081/api/value-comparer/all-items");
    let responseJson = await response.json();

    // push item objs to the itemArray to easily generate the table
    let itemArr = [];

    for (let i = 0; i < responseJson.length; i++) {
        itemArr.push(responseJson[i]);
    }

    return itemArr;
}

// create table row for the header
function createTableHeader() {
    // create table row for the header
    let tr = document.createElement("tr");

    // create table headers and attach to the tr
    let thId = genElement("th", "id");
    let thName = genElement("th", "name");
    let thAmount = genElement("th", "amount");
    let thPrice = genElement("th", "price");
    let thUnit = genElement("th", "unit");
    let thBrand = genElement("th", "brand");
    let thStore = genElement("th", "store");
    let thCategory = genElement("th", "category");
    let thPricePerBaseAmount = genElement("th", "unit price");

    tr.appendChild(thId);
    tr.appendChild(thName);
    tr.appendChild(thAmount);
    tr.appendChild(thPrice);
    tr.appendChild(thUnit);
    tr.appendChild(thBrand);
    tr.appendChild(thStore);
    tr.appendChild(thCategory);
    tr.appendChild(thPricePerBaseAmount);

    tr.id = "item-table-header";
    return tr;
}

// create table row for the body
function createTableBody(itemArr) {
    let trArr = [];

    if (itemArr.length != 0) {
        for (let i = 0; i < itemArr.length; i++) {
            let tr = document.createElement("tr");

            let tdId = genElement("td", itemArr[i].id);
            let tdName = genElement("td", itemArr[i].name);
            let tdAmount = genElement("td", itemArr[i].amount);
            let tdPrice = genElement("td", `$${itemArr[i].price}`);
            let tdUnit = genElement("td", itemArr[i].unit);
            let tdBrand = genElement("td", itemArr[i].brand);
            let tdStore = genElement("td", itemArr[i].store);
            let tdCategory = genElement("td", itemArr[i].category);
            let tdPricePerBaseAmount;

            // check if the item's base unit is mass/volume/length
            if (massUnitsLst.includes(itemArr[i].unit) || volumeUnitsLst.includes(itemArr[i].unit)) {
                tdPricePerBaseAmount = genElement("td", `$${itemArr[i].pricePerBaseAmount.toFixed(3)} / oz`);
            } else if (lengthUnitsLst.includes(itemArr[i].unit)) {
                tdPricePerBaseAmount = genElement("td", `$${itemArr[i].pricePerBaseAmount.toFixed(3)} / in`);
            }

            // create deleteBtn so that the user can delete the item from the database
            let tdDeleteBtn = genElement("button", "Delete");
            tdDeleteBtn.addEventListener("click", async() => {
                await deleteItem(itemArr[i].id);
                clearTable();

                let updatedItemArr = await getAllItems();
                createTable(updatedItemArr);
            })

            tr.appendChild(tdId);
            tr.appendChild(tdName);
            tr.appendChild(tdAmount);
            tr.appendChild(tdPrice);
            tr.appendChild(tdUnit);
            tr.appendChild(tdBrand);
            tr.appendChild(tdStore);
            tr.appendChild(tdCategory);
            tr.appendChild(tdPricePerBaseAmount);
            tr.appendChild(tdDeleteBtn);

            trArr.push(tr);
        }
    }
    return trArr;
}

// update the table header and the body with itemArr received
function createTable(itemArr) {
    let itemTable = document.getElementById("item-table");

    let tableHeader = createTableHeader();
    itemTable.appendChild(tableHeader);

    let tableDataRowArr = createTableBody(itemArr);

    for (let i = 0; i < tableDataRowArr.length; i++) {
        itemTable.appendChild(tableDataRowArr[i]);
    }
}

// delete all contents from the table
function clearTable() {
    let itemTable = document.getElementById("item-table");
    // while (itemTable.rows.length > 0) {
    //     itemTable.deleteRow(0);
    // }

    itemTable.replaceChildren();
}

// delete an item in the db sending a request to the back end
async function deleteItem(itemId) {
    let response = await fetch(`http://localhost:8081/api/value-comparer/item/${itemId}`, { method: "DELETE" });
}





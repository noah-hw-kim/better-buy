let unitTypeLst = [];
let massUnitsLst = [];
let volumeUnitsLst = [];
let lengthUnitsLst = [];
let categoryLst = [" "];
let itemCount = 2;

setUpListsAndDB();
setUpBtns();

/**
 * PAGE SETUP ---------------------------------------------------------------------------------------------------------------
 */

// Load unitType, units, categories, and search history from the server 
async function setUpListsAndDB() {
    await getCategories();
    genCategoryDropdown("category-dropdown-1");
    genCategoryDropdown("category-dropdown-2");

    await getUnitsAndUnitTypes();
    genUnitAndUnitTypeDropdowns("unit-type-dropdown-1", "unit-dropdown-1");
    genUnitAndUnitTypeDropdowns("unit-type-dropdown-2", "unit-dropdown-2");

    let searchedItemsArr = await getAllItems();
    genSearchTable(searchedItemsArr);
}

// Api call: Get categories from backend repository (which will populate dropdowns in genCategoryDropdown())
async function getCategories() {
    let response = await fetch("http://localhost:8081/api/better-buy/categories");
    let responseJson = await response.json();
    let jsonCategoryList = responseJson["categories"]; 
    let jsonCategoryValues = Object.values(jsonCategoryList);
    categoryLst.push(...jsonCategoryValues);
    categoryLst.sort();
}

// Api call: Get units and unit types from backend repository (which will populate dropdowns in genUnitDropdown())
async function getUnitsAndUnitTypes() {
    let response = await fetch("http://localhost:8081/api/better-buy/unit-list");
    let responseJson = await response.json();
    let jsonUnitList = responseJson["unitTypeToUnit"];
    let jsonUnitListKeys = Object.keys(jsonUnitList);

    for (let i = 0; i < jsonUnitListKeys.length; i++) {
        let currKey = jsonUnitListKeys[i];              
        unitTypeLst.push(currKey);                      

        if (currKey.includes("mass")) {
            massUnitsLst.push(...jsonUnitList[currKey]);
        } else if (currKey.includes("volume")) {
            volumeUnitsLst.push(...jsonUnitList[currKey]);
        } else if (currKey.includes("length")) {
            lengthUnitsLst.push(...jsonUnitList[currKey]);
        }
    }

    unitTypeLst.sort();
    massUnitsLst.sort();
    volumeUnitsLst.sort();
    lengthUnitsLst.sort();
}

// Api call: Get all records from backend repository (which will populate search history table)
async function getAllItems() {
    let response = await fetch("http://localhost:8081/api/better-buy/all-items");
    let responseJson = await response.json();
    let searchedItemsArr = [];

    for (let i = 0; i < responseJson.length; i++) {
        searchedItemsArr.push(responseJson[i]);
    }
    return searchedItemsArr;
}

// Setup Unit and Unit Type Dropdowns
function genUnitAndUnitTypeDropdowns(unitTypeDropdownID, unitDropdownID) {
    let unitTypeDropdown = document.getElementById(unitTypeDropdownID);

    genUnitTypeDropdown(unitTypeDropdownID);
    genUnitDropdown(unitTypeDropdownID, unitDropdownID);

    unitTypeDropdown.addEventListener("change", () => genUnitDropdown(unitTypeDropdownID, unitDropdownID));
}

// Helper method for genUnitAndUnitTypeDropdowns: Generates Unit Type dropdown ("volume", "mass", or "length")
function genUnitTypeDropdown(unitTypeDropdownID) {
    regenerateChildren("option", unitTypeLst, unitTypeDropdownID);
}

// Helper method for genUnitAndUnitTypeDropdowns: Generates unit dropdown corresponding to unit type selected
function genUnitDropdown(unitTypeDropdownID, unitDropdownID) {
    let unitTypeDropdown = document.getElementById(unitTypeDropdownID);

    if (unitTypeDropdown.value == "length") {
        regenerateChildren("option", lengthUnitsLst, unitDropdownID);
    } else if (unitTypeDropdown.value == "mass") {
        regenerateChildren("option", massUnitsLst, unitDropdownID);
    } else if (unitTypeDropdown.value == "volume") {
        regenerateChildren("option", volumeUnitsLst, unitDropdownID);
    }
}

// Generates Category dropdown for provided category dropdown element id
function genCategoryDropdown(categoryDropdownID) {
    regenerateChildren("option", categoryLst, categoryDropdownID);
}


/**
 * EVENT HANDLERS ---------------------------------------------------------------------------------------------------------------
 */

// Add event handlers for all page buttons
function setUpBtns() {
    let addItemBtn = document.getElementById("add-item-button");
    let removeBtn = document.getElementById("remove-item-button");
    let searchRecordsBtn = document.getElementById("search-records-button");
    let resetBtn = document.getElementById("reset-button");
    let compareForm = document.getElementById("compare-form");
    
    // handle add button (adds item component)
    addItemBtn.addEventListener("click", () => {
        itemCount++;
        handleAddBtn();
        if (itemCount > 2) {
            removeBtn.disabled = false;
        }
        if (itemCount >= 4) {
            addItemBtn.disabled = true;
        }
    })

    // handle remove button (removes item component)
    removeBtn.addEventListener("click", () => {
        handleRemoveBtn();
        itemCount--;

        if (itemCount <= 2) {
            removeBtn.disabled = true;
        }
        if (itemCount < 4) {
            addItemBtn.disabled = false;
        }
    })

    // handle search button (searches in search history table)
    searchRecordsBtn.addEventListener("click", async() => {
        let searchedRecordsArr = await handleSearchBtn();

        clearTable();

        if (searchedRecordsArr.length > 0) {
            genSearchTable(searchedRecordsArr);
        }
    })

    // handle reset button (clears all item component input)
    resetBtn.addEventListener("click", () => {
        compareForm.reset();
    })

    // handle submit (compare button)
    const forms = document.querySelectorAll('.needs-validation');

    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            event.preventDefault();
            if (!form.checkValidity()) {
                event.stopPropagation();
            } 
            else {
                form.classList.add('was-validated')
                handleSubmit();
            }
        }, false)
    })
}

// Adds another item component to page
function handleAddBtn() {
    let item1Div = document.getElementById("item1");
    let newItemDiv = item1Div.cloneNode(true);
    newItemDiv.id = `item${itemCount}`;

    let h5AndSelectElem = newItemDiv.querySelectorAll("h5, select");

    for (i = 0; i < h5AndSelectElem.length; i++) {
        let currName = h5AndSelectElem[i].getAttribute("name");

        // update item title (e.x, "Item 1" -> "Item 3")
        if (currName == "item-index") {
            h5AndSelectElem[i].textContent = `Item ${itemCount}`;
        }
        // update ids (e.x., "unit-dropdown-1" -> "unit-dropdown-3")
        else if (currName == "unit-type") {
            h5AndSelectElem[i].id = `unit-type-dropdown-${itemCount}`;
        } else if (currName == "unit") {
            h5AndSelectElem[i].id = `unit-dropdown-${itemCount}`;
        } else if (currName == "category") {
            h5AndSelectElem[i].id = `category-dropdown-${itemCount}`;
        }
    }

    // append new item div to page and add in event
    let formElem = document.getElementById("form-body");
    formElem.appendChild(newItemDiv);
    genUnitAndUnitTypeDropdowns(`unit-type-dropdown-${itemCount}`, `unit-dropdown-${itemCount}`)
}

// Removes last added item component from page
function handleRemoveBtn() {
    let formElem = document.getElementById("form-body");
    let targetItem = document.getElementById(`item${itemCount}`);
    formElem.removeChild(targetItem);
}

// Search items from backend repository
async function handleSearchBtn() {
    let searchText = document.getElementById("search-text").value;
    let searchReturnArr = [];

    if (searchText == "") {
        return getAllItems();
    } else {
        let response = await fetch(`http://localhost:8081/api/better-buy/items/${searchText}`);
        let responseJson = await response.json();

        for (let i = 0; i < responseJson.length; i++) {
            searchReturnArr.push(responseJson[i]);
        }
    }
    return searchReturnArr; 
}

// Save items in backend repository, compare items, and display the result
async function handleSubmit() {
    let itemsArr = document.getElementsByClassName("item");

    if (validateInputs(itemsArr) != false) {
        let itemsArrJson = await saveToDB(itemsArr);
        let updatedSearchedRecordsArr = await getAllItems();

        clearTable();
        genSearchTable(updatedSearchedRecordsArr);

        let comparisionResult = await compareItems(itemsArrJson);

        displayCompareResult(comparisionResult);
    }
}

// Helper method for handleSubmit()
function validateInputs(searchedItemsArr) {
    isValid = true; 
    
    for (let i = 0; i < searchedItemsArr.length; i++) {
        let divInputs = searchedItemsArr[i].getElementsByTagName('input');

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

// Helper method for handleSubmit()
async function saveToDB(searchedItemsArr) {
    let jsonData = jsonifysearchedItemsArr(searchedItemsArr);
    let response = await fetch("http://localhost:8081/api/better-buy/items", {
        method: "POST", headers: {
            "Content-Type": "application/json",
        }, body: JSON.stringify(jsonData)
    });
    let searchedItemsArrJson = await response.json();

    return searchedItemsArrJson;
}

// Helper method for saveToDB()
function jsonifysearchedItemsArr(searchedItemsArr) {
    let jsonData = [];

    for (let i = 0; i < searchedItemsArr.length; i++) {
        let jsonElem = {};
        let divInputs = searchedItemsArr[i].getElementsByTagName('input');
        let divSelects = searchedItemsArr[i].getElementsByTagName('select');

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

// Helper method for handleSubmit(): Get comparison result (best value item and compared items list)
async function compareItems(searchedItemsArrJson) {
    let idStr = getIdFromJsonArr(searchedItemsArrJson);
    let response = await fetch(`http://localhost:8081/api/better-buy/item-comparison/${idStr}`)
    let comparisionResult = await response.json();
    return comparisionResult;
}

// Helper method for compareItems(): Get ID from json array passed back from backend repository
function getIdFromJsonArr(searchedItemsArrJson) {
    let idStr = "";

    for (i = 0; i < searchedItemsArrJson.length; i++) {
        idStr += searchedItemsArrJson[i].id + ",";
    }

    idStr = idStr.substring(0, idStr.length - 1);

    return idStr;
}

// Helper method for handleSubmit():Update the compare-result div with comparison result
function displayCompareResult(responseJson) {
    let result = document.getElementById("compare-result");
    let bestValItem = responseJson.bestValItem;
    let comparedItemsList = responseJson.comparedItemsList;
    let brand = bestValItem.brand;
    let name = bestValItem.name;
    let price = bestValItem.price;
    let amount = bestValItem.amount;
    let unit = bestValItem.unit;
    let comparedItemsStr = `${brand} ${name} ($${price} for ${amount} ${unit}) is of best value.\n\n`;

    for (let i = 0; i < comparedItemsList.length; i++) {
        let comparedItemsList = responseJson.comparedItemsList;

        // sort by lowest price per base unit
        comparedItemsList.sort((a,b) => {
            return a.pricePerBaseUnit-b.pricePerBaseUnit;
        })

        let item = comparedItemsList[i];
        comparedItemsStr += `${item.brand} ${item.name} (${item.amount} ${item.unit}): $${item.pricePerBaseUnit}/${item.baseUnit}\n`;
    }

    let compareResultStr = genElement("p", comparedItemsStr);
    result.replaceChildren();
    result.appendChild(compareResultStr);
}

// Delete an item in the backend repository
async function deleteRecord(itemId) {
    await fetch(`http://localhost:8081/api/better-buy/item/${itemId}`, { method: "DELETE" });
}

// Delete all items in the backend repository
async function deleteAllRecords() {
    await fetch(`http://localhost:8081/api/better-buy/items`, { method: "DELETE" });
}


/**
 * UTILITY ---------------------------------------------------------------------------------------------------------------
 */

// Returns a single element with text (e.x., "<option>Option1</option>")
function genElement(elementType, text) {
    let newElement = document.createElement(elementType);
    newElement.innerText = text;
    return newElement;
}

// Clears parent element of its current children and regenerates new children from elementsLst
function regenerateChildren(elementType, elementsLst, parentID) {
    let parent = document.getElementById(parentID);
    parent.replaceChildren();

    for (let i = 0; i < elementsLst.length; i++) {
        let newText = elementsLst[i];
        let newElement = genElement(elementType, newText);
        parent.appendChild(newElement);
    }
}

// Recreate table and update body with new item inputs
function genSearchTable(searchedItemsArr) {
    clearTable();
    
    let itemTable = document.getElementById("item-table");
    let itemHeader = document.createElement("thead");
    let itemBody = document.createElement("tbody");

    let tableHeader = genSearchHeader();
    itemHeader.appendChild(tableHeader);
    itemTable.appendChild(itemHeader);

    if (searchedItemsArr != null && searchedItemsArr.length > 0) {
        let tableDataRowArr = genSearchBody(searchedItemsArr);
        for (let i = 0; i < tableDataRowArr.length; i++) {
            itemBody.appendChild(tableDataRowArr[i]);
        }
        itemTable.appendChild(itemBody);
    }
}

// Clear table contents (this does not delete records from backend repo)
function clearTable() {
    let itemTable = document.getElementById("item-table");
    itemTable.replaceChildren();
}

// Create table header row
function genSearchHeader() {
    // create table row for the header
    let tr = document.createElement("tr");

    // create table headers and attach to the tr
    let thName = genElement("th", "name");
    let thAmount = genElement("th", "amount");
    let thPrice = genElement("th", "price");
    let thUnit = genElement("th", "unit");
    let thBrand = genElement("th", "brand");
    let thStore = genElement("th", "store");
    let thCategory = genElement("th", "category");
    let thPricePerBaseUnit = genElement("th", "unit price");
    let thClearBtn = genElement("button", "Clear");
    thClearBtn.id = "clear-button";

    // add even to the clear button that delete all items in db
    thClearBtn.addEventListener("click", () => {
        deleteAllRecords();
        genSearchTable();
    })

    tr.append(thName, thAmount, thPrice, thUnit, thBrand, thStore, thCategory, thPricePerBaseUnit, thClearBtn)
    tr.id = "item-table-header";
    return tr;
}

// Create table rows for each item submitted
function genSearchBody(searchedItemsArr) {
    let trArr = [];

    if (searchedItemsArr.length != 0) {
        for (let i = 0; i < searchedItemsArr.length; i++) {
            let tr = document.createElement("tr");

            let tdName = genElement("td", searchedItemsArr[i].name);
            let tdAmount = genElement("td", searchedItemsArr[i].amount);
            let tdPrice = genElement("td", `$${searchedItemsArr[i].price}`);
            let tdUnit = genElement("td", searchedItemsArr[i].unit);
            let tdBrand = genElement("td", searchedItemsArr[i].brand);
            let tdStore = genElement("td", searchedItemsArr[i].store);
            let tdCategory = genElement("td", searchedItemsArr[i].category);
            let tdPricePerBaseUnit;

            // check if the item's base unit is mass/volume/length
            if (massUnitsLst.includes(searchedItemsArr[i].unit) || volumeUnitsLst.includes(searchedItemsArr[i].unit)) {
                tdPricePerBaseUnit = genElement("td", `$${searchedItemsArr[i].pricePerBaseUnit.toFixed(3)} / oz`);
            } else if (lengthUnitsLst.includes(searchedItemsArr[i].unit)) {
                tdPricePerBaseUnit = genElement("td", `$${searchedItemsArr[i].pricePerBaseUnit.toFixed(3)} / in`);
            }

            // create deleteBtn so that the user can delete the item from the database
            let tdDeleteBtn = genElement("button", "x");
            tdDeleteBtn.addEventListener("click", async() => {
                await deleteRecord(searchedItemsArr[i].id);
                clearTable();
                let updatedsearchedItemsArr = await getAllItems();
                genSearchTable(updatedsearchedItemsArr);
            })

            tr.append(tdName, tdAmount, tdPrice, tdUnit, tdBrand, tdStore, tdCategory, tdPricePerBaseUnit, tdDeleteBtn)
            trArr.push(tr);
        }
    }
    return trArr;
}
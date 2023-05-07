// need to add required feature for name, price, and amount when + and -

// indicate current number of items
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

// Load unitType, units, and the categories from the server 
async function setUpListsAndDB() {
    await getCategories();

    let categoryDropdown1 = document.getElementById("category-dropdown-1");
    let categoryDropdown2 = document.getElementById("category-dropdown-2");
    genCategoryDropdown(categoryDropdown1);
    genCategoryDropdown(categoryDropdown2);

    await getUnitsAndUnitTypes();

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

// Api call: Get units and unit types from backend repository (which will populate dropdowns in genUnitDropdown())
async function getUnitsAndUnitTypes() {
    let response = await fetch("http://localhost:8081/api/value-comparer/unit-list");
    let responseJson = await response.json();
    let jsonUnitList = responseJson["unitList"];
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
}

// Api call: Get categories from backend repository (which will populate dropdowns in genCategoryDropdown())
async function getCategories() {
    let response = await fetch("http://localhost:8081/api/value-comparer/categories");
    let responseJson = await response.json();
    let jsonCategoryList = responseJson["categories"]; 
    let jsonCategoryValues = Object.values(jsonCategoryList);
    categoryLst.push(...jsonCategoryValues);
}

// Api call: Get all records from backend repository (which will populate search history table)
async function getAllItems() {
    let response = await fetch("http://localhost:8081/api/value-comparer/all-items");
    let responseJson = await response.json();
    let itemArr = [];

    for (let i = 0; i < responseJson.length; i++) {
        itemArr.push(responseJson[i]);
    }
    return itemArr;
}

// Generates Unit Type dropdown and its corresponding unit dropdown
function genUnitDropdown(unitTypeDropdown, unitDropdown) {
    if (unitTypeDropdown.value == "length") {
        regenerateChildren("option", lengthUnitsLst, unitDropdown);
    } else if (unitTypeDropdown.value == "mass") {
        regenerateChildren("option", massUnitsLst, unitDropdown);
    } else if (unitTypeDropdown.value == "volume") {
        regenerateChildren("option", volumeUnitsLst, unitDropdown);
    }
}

// Generates Category dropdown 
function genCategoryDropdown(categoryDropdown) {
    regenerateChildren("option", categoryLst, categoryDropdown);
}


/**
 * EVENT HANDLERS ---------------------------------------------------------------------------------------------------------------
 */

// Add event handlers for all page buttons
function setUpBtns() {
    let addItemBtn = document.getElementById("add-item-button");
    let removeBtn = document.getElementById("remove-item-button");
    let searchItemBtn = document.getElementById("search-item-button");
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
    searchItemBtn.addEventListener("click", async() => {
        let itemArr = await handleSearchBtn();

        clearTable();

        if (itemArr.length > 0) {
            createTable(itemArr);
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

        // update item title (e.x, "Item 1" -> "Item 2")
        if (currName == "item-index") {
            h5AndSelectElem[i].textContent = `Item ${itemCount}`;
        }
        // update ids
        else if (currName == "unit-type") {
            h5AndSelectElem[i].id = `unit-type-dropdown-${itemCount}`;
        } else if (currName == "unit") {
            h5AndSelectElem[i].id = `unit-dropdown-${itemCount}`;
        } else if (currName == "category") {
            h5AndSelectElem[i].id = `category-dropdown-${itemCount}`
        }
    }

    let formElem = document.getElementById("form-body");
    formElem.appendChild(newItemDiv);

    let newCategoryDropdown = document.getElementById(`category-dropdown-${itemCount}`);
    genCategoryDropdown(newCategoryDropdown);

    let newUnitTypeDropdown = document.getElementById(`unit-type-dropdown-${itemCount}`);
    let newUnitDropdown = document.getElementById(`unit-dropdown-${itemCount}`);
    genUnitDropdown(newUnitTypeDropdown, newUnitDropdown);

    newUnitTypeDropdown.addEventListener("change", () => genUnitDropdown(newUnitTypeDropdown, newUnitDropdown));
}

// Removes last added item component from page
function handleRemoveBtn() {
    let formElem = document.getElementById("form-body");
    let targetItem = document.getElementById(`item${itemCount}`);
    formElem.removeChild(targetItem);
}

// Search items from backend repository
async function handleSearchBtn() {
    let text = document.getElementById("search-text").value;
    let itemArr = [];

    if (text == "") {
        return getAllItems();
    } else {
        let response = await fetch(`http://localhost:8081/api/value-comparer/items/${text}`);
        let responseJson = await response.json();

        for (let i = 0; i < responseJson.length; i++) {
            itemArr.push(responseJson[i]);
        }
    }
    return itemArr; 
}

// Save items in backend repository, compare items, and display the result
async function handleSubmit() {
    let itemArr = document.getElementsByClassName("item");

    if (validateInputs(itemArr) != false) {
        let itemArrJson = await saveToDB(itemArr);
        let updatedItemArr = await getAllItems();

        clearTable();
        createTable(updatedItemArr);

        let comparisionResult = await compareItems(itemArrJson);

        displayCompareResult(comparisionResult);
    }
}

// Helper method for handleSubmit()
function validateInputs(itemArr) {
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

// Helper method for handleSubmit()
async function saveToDB(itemArr) {
    let jsonData = jsonifyItemArr(itemArr);
    let response = await fetch("http://localhost:8081/api/value-comparer/items", {
        method: "POST", headers: {
            "Content-Type": "application/json",
        }, body: JSON.stringify(jsonData)
    });
    let itemArrJson = await response.json();

    return itemArrJson;
}

// Helper method for saveToDB()
function jsonifyItemArr(itemArr) {
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

// Helper method for handleSubmit(): Get comparison result (best value item and compared items list)
async function compareItems(itemArrJson) {
    let idStr = getIdFromJsonArr(itemArrJson);
    let response = await fetch(`http://localhost:8081/api/value-comparer/item-comparison/${idStr}`)
    let comparisionResult = await response.json();

    return comparisionResult;
}

// Helper method for compareItems(): Get ID from json array passed back from backend repository
function getIdFromJsonArr(itemArrJson) {
    let idStr = "";

    for (i = 0; i < itemArrJson.length; i++) {
        idStr += itemArrJson[i].id + ",";
    }

    idStr = idStr.substring(0, idStr.length - 1);

    return idStr;
}

// Helper method for handleSubmit():Update the compare-result div with comparison result
function displayCompareResult(responseJson) {
    let result = document.getElementById("compare-result");
    let bestValItem = responseJson.bestValItem;
    let comparedItemsList = responseJson.comparedItemsList;
    let comparedItemsStr = "";
    comparedItemsStr += `${bestValItem.name} is of best value.\n\n`;

    for (let i = 0; i < comparedItemsList.length; i++) {
        comparedItemsStr += `${comparedItemsList[i].name}: $ ${comparedItemsList[i].pricePerBaseUnit}/${comparedItemsList[i].unit}\n`;
    }

    let compareResultStr = genElement("p", comparedItemsStr);
    result.replaceChildren();
    result.appendChild(compareResultStr);
}

// Delete an item in the backend repository
async function deleteRecord(itemId) {
    await fetch(`http://localhost:8081/api/value-comparer/item/${itemId}`, { method: "DELETE" });
}

// Delete all items in the backend repository
async function deleteAllRecords() {
    await fetch(`http://localhost:8081/api/value-comparer/items`, { method: "DELETE" });
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
function regenerateChildren(elementType, elementsLst, parent) {
    parent.replaceChildren();

    for (let i = 0; i < elementsLst.length; i++) {
        let newText = elementsLst[i];
        let newElement = genElement(elementType, newText);
        parent.appendChild(newElement);
    }
}

// Recreate table and update body with new item inputs
function createTable(itemArr) {
    clearTable();
    
    let itemTable = document.getElementById("item-table");
    let itemHeader = document.createElement("thead");
    let itemBody = document.createElement("tbody");

    let tableHeader = createTableHeader();
    itemHeader.appendChild(tableHeader);
    itemTable.appendChild(itemHeader);

    if (itemArr != null && itemArr.length > 0) {
        let tableDataRowArr = createTableBody(itemArr);
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
    let thPricePerBaseUnit = genElement("th", "unit price");
    let thClearBtn = genElement("button", "Clear");
    thClearBtn.id = "clear-button";

    // add even to the clear button that delete all items in db
    thClearBtn.addEventListener("click", () => {
        deleteAllRecords();
        createTable();
    })

    tr.append(thId, thName, thAmount, thPrice, thUnit, thBrand, thStore, thCategory, thPricePerBaseUnit, thClearBtn)
    tr.id = "item-table-header";
    return tr;
}

// Create table rows for each item submitted
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
            let tdPricePerBaseUnit;

            // check if the item's base unit is mass/volume/length
            if (massUnitsLst.includes(itemArr[i].unit) || volumeUnitsLst.includes(itemArr[i].unit)) {
                tdPricePerBaseUnit = genElement("td", `$${itemArr[i].pricePerBaseUnit.toFixed(3)} / oz`);
            } else if (lengthUnitsLst.includes(itemArr[i].unit)) {
                tdPricePerBaseUnit = genElement("td", `$${itemArr[i].pricePerBaseUnit.toFixed(3)} / in`);
            }

            // create deleteBtn so that the user can delete the item from the database
            let tdDeleteBtn = genElement("button", "x");
            tdDeleteBtn.addEventListener("click", async() => {
                await deleteRecord(itemArr[i].id);
                clearTable();
                let updatedItemArr = await getAllItems();
                createTable(updatedItemArr);
            })

            tr.append(tdId, tdName, tdAmount, tdPrice, tdUnit, tdBrand, tdStore, tdCategory, tdPricePerBaseUnit, tdDeleteBtn)
            trArr.push(tr);
        }
    }
    return trArr;
}
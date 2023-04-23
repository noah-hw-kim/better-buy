let unitTypeLst = [];
let categoryLst = [];
let massUnitsLst = [];
let volumeUnitsLst = [];
let lengthUnitsLst = [];

defaultUnitSetting();

let categoryDropdown1 = document.getElementById("category-dropdown-1");
let categoryDropdown2 = document.getElementById("category-dropdown-2");

let unitTypeDropdown1 = document.getElementById("unit-type-dropdown-1");
let unitDropdown1 = document.getElementById("unit-dropdown-1");
unitTypeDropdown1.addEventListener("change", () => genUnitDropdown(unitTypeDropdown1, unitDropdown1));

let unitTypeDropdown2 = document.getElementById("unit-type-dropdown-2");
let unitDropdown2 = document.getElementById("unit-dropdown-2");
unitTypeDropdown2.addEventListener("change", () => genUnitDropdown(unitTypeDropdown2, unitDropdown2));

let compareBtn = document.getElementById("compare-button");
compareBtn.addEventListener("click", () => {
    let items = document.getElementsByClassName("item");
    
    // compare function needs to wait for the jsonResponse 1 and 2
    compareItems(items, saveItems);

    // getAllUnits();
});

getAllUnits();


// create an item class
class Item{
    constructor(id, name, amount, price, unit, brand, store, category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.unit = unit;
        this.brand = brand;
        this.store = store;
        this.category = category;
    }
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

async function genCategory() {
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
 * Wait to generate UnitLists and generates first unit Type dropdown and its corresponding unit dropdown (default = "mass")
 */
async function defaultUnitSetting() {
    const result1 = await genUnitLists();
    const result2 = await genCategory();

    genCategoryDropdown(categoryDropdown1);
    genCategoryDropdown(categoryDropdown2);
    genUnitDropdown(unitTypeDropdown1, unitDropdown1);
    genUnitDropdown(unitTypeDropdown2, unitDropdown2);
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
    //let unitTypeDropdown = document.getElementById("unit-type-dropdown-1");
    //let unitDropdown = document.getElementById("unit-dropdown-1");

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

async function saveItems(itemDivs) {
    let jsonData = formatToJson(itemDivs);
    let response = await fetch("http://localhost:8081/api/value-comparer/new-items", {method: "POST", headers: {
        "Content-Type": "application/json",
      }, body:JSON.stringify(jsonData)});
    
    let responseJson = await response.json();
    return responseJson;
}

/**
 * need to handle both inputs and selects elements in the targetDiv, based on the name and value, converts to the Json obj and return it.
 */

function formatToJson(items) {
    let jsonData = [];

    for (let i = 0; i < items.length; i++) {
        let jsonElem = {};
        let divInputs = items[i].getElementsByTagName('input');
        let divSelects = items[i].getElementsByTagName('select');

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

async function compareItems(items, saveItems) {
    let jsonItem = await saveItems(items);

    let itemIdList = "";
    for (i = 0; i < jsonItem.length; i++) {
        itemIdList += jsonItem[i].id + ",";
    }

    itemIdList = itemIdList.substring(0, itemIdList.length - 1);

    let response = await fetch(`http://localhost:8081/api/value-comparer/item-comparison/${itemIdList}`)
    let responseJson = await response.json();

    betterItem(responseJson);
}

function betterItem(responseJson) {
    let result = document.getElementById("compare-result");
    let betterItem = responseJson.betterValue;
    let comparedItemsList = responseJson.comparedItemsList;
    // how much the better item is cheaper than the average
    let valueComparison = responseJson.valueComparison;
    let compareItemNames = "";

    for (let i = 0; i < comparedItemsList.length; i++) {
        // exclude the better item from the compared Item List and format it
        if (comparedItemsList[i].name != betterItem.name) {
            compareItemNames += comparedItemsList[i].name + ", ";
        }
    }

    // format the item names
    compareItemNames = compareItemNames.trim().substring(0, compareItemNames.length-2);

    let elem = genElement("p", `${betterItem.name} is ${valueComparison.toFixed(3)} times cheaper than the average of the other items(${compareItemNames})`);

    result.replaceChildren();
    result.appendChild(elem);
}

async function getAllUnits(clearTable) {
    let response = await fetch("http://localhost:8081/api/value-comparer/all-items");
    let responseJson = await response.json();
    
    // push item objs to the itemArray to easily generate the table
    let itemArr = [];

    for (let i = 0; i < responseJson.length; i++) {
        itemArr.push(responseJson[i]);
    }
    createTable(itemArr);
}

function createTableHeaderRow() {
    // create table row for the header
    let tr = document.createElement("tr");

    // create table headers and attach to the tr
    let thId = genElement("th", "id");
    let thName = genElement("th", "name");
    let thAmount = genElement("th", "amount");
    let thUnit = genElement("th", "unit");
    let thPrice = genElement("th", "price");
    let thBrand = genElement("th", "brand");
    let thStore = genElement("th", "store");
    let thCategory = genElement("th", "category");
    let thPricePerBaseAmount = genElement("th", "$ per base unit (oz/in)");

    tr.appendChild(thId);
    tr.appendChild(thName);
    tr.appendChild(thAmount);
    tr.appendChild(thUnit);
    tr.appendChild(thPrice);
    tr.appendChild(thBrand);
    tr.appendChild(thStore);
    tr.appendChild(thCategory);
    tr.appendChild(thPricePerBaseAmount);

    return tr;
}

function createTableDataRows(itemArr) {
    let trArr = [];

    for (let i = 0; i < itemArr.length; i++) {
        let tr = document.createElement("tr");

        let tdId = genElement("td", itemArr[i].id);
        let tdName = genElement("td", itemArr[i].name);
        let tdAmount = genElement("td", itemArr[i].amount);
        let tdUnit = genElement("td", itemArr[i].unit);
        let tdPrice = genElement("td", itemArr[i].price);
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

        tr.appendChild(tdId);
        tr.appendChild(tdName);
        tr.appendChild(tdAmount);
        tr.appendChild(tdUnit);
        tr.appendChild(tdPrice);
        tr.appendChild(tdBrand);
        tr.appendChild(tdStore);
        tr.appendChild(tdCategory);
        tr.appendChild(tdPricePerBaseAmount);

        trArr.push(tr);
    }

    return trArr;
}

function createTable(itemArr) {
    let itemTable = document.getElementById("item-table");

    let tableHeaderRow = createTableHeaderRow();
    itemTable.appendChild(tableHeaderRow);

    let tableDataRowArr = createTableDataRows(itemArr);

    for (let i = 0; i < tableDataRowArr.length; i++) {
        itemTable.appendChild(tableDataRowArr[i]);
    }
}

function clearTable() {
    let itemTable = document.getElementById("item-table");
    itemTable.replaceChildren();
}





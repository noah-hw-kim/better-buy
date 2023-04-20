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
    let itemDivs = document.getElementsByTagName('div')
    
    // let jsonResponse1 = saveItem(item1Div);
    // let jsonResponse2 = saveItem(item2Div);

    // compare function needs to wait for the jsonResponse 1 and 2
    compareTwoItems(itemDivs, saveItem);



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
    // let jsonVals = Object.values(responseJson);

    // for (let i=0;i<jsonVals.length;i++) {
    //     let jsonValKeys = Object.keys(jsonVals[i]);
    //     jsonList = jsonValKeys;
    // }

    // let jsonKeys = Object.keys(responseJson);   // returns array of keys
    // console.log(jsonKeys);

    // for (let i=0; i< jsonKeys.length; i++) {    // for each item in jsonKeys
    //     let currKey = jsonKeys[i];              // get the key

    //     if (currKey.includes("mass")) {
    //         unitTypeLst.push("mass");
    //         massUnitsLst.push(...responseJson[currKey]);
    //     } else if (currKey.includes("volume")) {
    //         unitTypeLst.push("volume");
    //         volumeUnitsLst.push(...responseJson[currKey]);
    //     } else if (currKey.includes("length")) {
    //         unitTypeLst.push("length");
    //         lengthUnitsLst.push(...responseJson[currKey]);
    //     }
    // }
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

/**
 * post json data to the database by sending data to the backend API 
 */

async function saveItem(targetDiv) {
    let jsonData = formatToJson(targetDiv);

    let response = await fetch("http://localhost:8081/api/value-comparer/create", {method: "POST", headers: {
        "Content-Type": "application/json",
      }, body:JSON.stringify(jsonData)});
    let responseJson = await response.json();

    return responseJson;
}

/**
 * need to handle both inputs and selects elements in the targetDiv, based on the name and value, converts to the Json obj and return it.
 */

function formatToJson(targetDiv) {
    let jsonData = {};
    let divInputs = targetDiv.getElementsByTagName('input');
    let divSelects = targetDiv.getElementsByTagName('select');

    for (let i = 0; i < divInputs.length; i++) {
        // change the value type from string to float if the fileds are amount and price.
        if (divInputs[i].name.includes("amount") || divInputs[i].name.includes("price")) {
            jsonData[divInputs[i].name] = parseFloat(divInputs[i].value);
        }
        else {
            jsonData[divInputs[i].name] = divInputs[i].value;
        }
    }

    for (let i = 0; i < divSelects.length; i++) {
        if (divSelects[i].name != "unit-type") {
            jsonData[divSelects[i].name] = divSelects[i].value;
        }
    }
    return jsonData;
}

async function compareTwoItems(itemDivs, saveItem) {
    // hard code to save 2 items
    let jsonSavedItem1 = await saveItem(itemDivs[1]);
    let jsonSavedItem2 = await saveItem(itemDivs[2]);
    let item1Id = jsonSavedItem1.id;
    let item2Id = jsonSavedItem2.id;
    
    // hard code to compare 2 items
    let response = await fetch(`http://localhost:8081/api/value-comparer/item1id/${item1Id}/item2id/${item2Id}`);
    let responseJson = await response.json();

    betterItem(responseJson)
}

function betterItem(responseJson) {
    let result = document.getElementById("compare-result");
    let betterItem = responseJson.betterValue;
    let comparedItemsList = responseJson.comparedItems;
    // how much the better item is cheaper than the average
    let valueComparison = responseJson.valueComparison;
    let compareItemNames = "";

    for (let i = 0; i < comparedItemsList.length; i++) {
        // make a string for the compared item list except the better value item
        if (comparedItemsList[i].name != betterItem.name) {
            compareItemNames += comparedItemsList[i].name + ", ";
        }
    }

    let elem = createElementWithText("p", `${betterItem.name} is ${valueComparison} times cheaper than the average of the other items(${compareItemNames})`);

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

function createElementWithText(tag, text) {
    let element = document.createElement(tag);
    element.innerText = text;

    return element;
}

function createTableHeaderRow() {
    // create table row for the header
    let tr = document.createElement("tr");

    // create table headers and attach to the tr
    let thId = createElementWithText("th", "id");
    let thName = createElementWithText("th", "name");
    let thAmount = createElementWithText("th", "amount");
    let thPrice = createElementWithText("th", "price");
    let thBrand = createElementWithText("th", "brand");
    let thStore = createElementWithText("th", "store");
    let thCategory = createElementWithText("th", "category");
    let thPricePerBaseAmount = createElementWithText("th", "Price Per BaseAmount");

    tr.appendChild(thId);
    tr.appendChild(thName);
    tr.appendChild(thAmount);
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

        let tdId = createElementWithText("td", itemArr[i].id);
        let tdName = createElementWithText("td", itemArr[i].name);
        let tdAmount = createElementWithText("td", itemArr[i].amount);
        let tdPrice = createElementWithText("td", itemArr[i].price);
        let tdBrand = createElementWithText("td", itemArr[i].brand);
        let tdStore = createElementWithText("td", itemArr[i].store);
        let tdCategory = createElementWithText("td", itemArr[i].category);
        let tdPricePerBaseAmount;

        // check if the item's base unit is mass/volume/length
        if (massUnitsLst.includes(itemArr[i].unit)) {
            tdPricePerBaseAmount = createElementWithText("td", `$${itemArr[i].pricePerBaseAmount.toFixed(3)}/oz`);
        }

        tr.appendChild(tdId);
        tr.appendChild(tdName);
        tr.appendChild(tdAmount);
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



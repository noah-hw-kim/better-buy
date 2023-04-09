let unitTypeLst=[];
let massUnitsLst=[];
let volumeUnitsLst=[];
let lengthUnitsLst=[];

genUnitLists();

let unitTypeDropdown1 = document.getElementById("unit-type-dropdown-1");
let unitDropdown1 = document.getElementById("unit-dropdown-1");
unitTypeDropdown1.addEventListener("change", () => genUnitDropdown(unitTypeDropdown1, unitDropdown1));

let unitTypeDropdown2 = document.getElementById("unit-type-dropdown-2");
let unitDropdown2 = document.getElementById("unit-dropdown-2");
unitTypeDropdown2.addEventListener("change", () => genUnitDropdown(unitTypeDropdown2, unitDropdown2));


/**
 * Generates list of unit types and units
 */
async function genUnitLists() {
    let response = await fetch("http://localhost:8080/api/value-comparer/unit-list");
    let responseJson = await response.json();

    let jsonKeys = Object.keys(responseJson);   // returns array of keys

    for (let i=0; i< jsonKeys.length; i++) {    // for each item in jsonKeys
        let currKey = jsonKeys[i];              // get the key

        if (currKey.includes("mass")) {
            unitTypeLst.push("mass");
            massUnitsLst.push(...responseJson[currKey]);
        } else if (currKey.includes("volume")) {
            unitTypeLst.push("volume");
            volumeUnitsLst.push(...responseJson[currKey]);
        } else if (currKey.includes("length")) {
            unitTypeLst.push("length");
            lengthUnitsLst.push(...responseJson[currKey]);
        }
    }
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

    for (let i=0; i<elementsLst.length; i++) {
        let newText = elementsLst[i];
        let newElement = genElement(elementType, newText);
        parent.appendChild(newElement);
    }
}

/**
 * Get better value
 */

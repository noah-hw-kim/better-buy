function genSections(numSections) {
    let parent = document.getElementById("compare-form")
    
    for (let i=1; i<=numSections; i++) {
        
        document.createElement("div")
        div.className = "item";

        let h2 = genElement("h2", `Item${i}`);
        let labelAmount = genElement("label", "Amount");
        let labelPrice = genElement("label", "Price");
        let labelUnitType = genElement("label", "UnitType");
        let labelUnit = genElement("label", "Unit");

        let optionLength = genElement("option", "length");
        let optionMass = genElement("option", "mass");
        let optionVolume = genElement("option", "volume");

    }
}

function genMassDropdowns() {
    lst = ["milligram (mg)",
            "gram (g)",
            "kilogram (kg)",
            "ounce (oz)", 
            "pound (lbs)",
            "ton (T)"
            ]
    
    genElements("select-unit", "option", lst);
}

function genLengthsDropdowns() {
    lst = ["inch (in)",
            "feet (ft)",
            "yard (yd)", 
            "mile (mi)",
            "milimeter (mm)",
            "centimeter (cm)",
            "meter (m)",
            "kilometer (km)"
            ]
    
    genElements("select-unit", "option", lst);
}

function genVolumeDropdowns() {
    lst = ["milliliter (ml)",
            "liter (l)",
            "kiloleter (kl)", 
            "cubic centimeter (cm3)",
            "fluid ounce (fl. oz.)",
            "gallon (gal)",
            "pint (pt)",
            ]
    
    genElements("select-unit", "option", lst);
}

function genElement(elementType, text) {
    let newElement = document.createElement(elementType);
    newElement.innerText = text;
    return newElement;
}

function genElements(elementsLst, parent) {
    let parent = document.getElementById(parentId);
    parent.replaceChildren();

    for (let i=0; i<elementsLst.length; i++) {
        parent.appendChild(elementsLst[i]);
    }
}

/**
 * Takes in a list of items and appends them as elementType to parent
 */
function genElements(parentId, elementType, itemsLst) {
    let parent = document.getElementById(parentId);
    parent.replaceChildren();

    for (let i=0; i<itemsLst.length; i++) {
        let text = itemsLst[i];
        let newElement = genElement(elementType, text);
        parent.appendChild(newElement);
    }
}

let unitTypeSelectors = document.getElementsByClassName("select-unit-type");
for (let i=0; i<unitTypeSelectors.length; i++) {
    let selector = unitTypeSelectors
    selector.addEventListener("change", () => {
        if (selector.value == "length") {
            genLengthsDropdowns();
        } else if (selector.value == "mass") {
            genMassDropdowns();
        } else if (selector.value == "volume") {
            genVolumeDropdowns();
        }
    })
}


/* let selectUnitType = document.getElementById("select-unit-type");
selectUnitType.addEventListener("change", ()=> {
    if (selectUnitType.value == "length") {
        genLengthsDropdowns();
    } else if (selectUnitType.value == "mass") {
        genMassDropdowns();
    } else if (selectUnitType.value == "volume") {
        genVolumeDropdowns();
    }
}) */
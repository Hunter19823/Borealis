function recursiveAddKeys(previous, current)
{
    var keys = Object.keys(current);
    for(let i=0;i<keys.length;i++){
        recursiveAdd(previous,keys[i],current[keys[i]]);
    }
}
function recursiveAdd(previous, key, value)
{
    var li = document.createElement('LI');
    previous.appendChild(li);
    var span = document.createElement('SPAN');
    li.appendChild(span);
    var ul;
    if(value === null){
        span = spanKey(li,key,"null");
        span.classList.add("null");
        li.classList.add("nulls");
    }else if(value.constructor === [].constructor){
        span.innerText = key;
        span.classList.add("caret");
        li.classList.add("objects");
        ul = document.createElement("UL");
        li.appendChild(ul);
        ul.classList.add("nested");
        ul.classList.add("object");
        recursiveAddKeys(ul,value);
    }else if(value.constructor === ({}).constructor) {
        span.innerText = key;
        span.classList.add("caret");
        li.classList.add("arrays");
        ul = document.createElement("UL");
        li.appendChild(ul);
        ul.classList.add("nested");
        ul.classList.add("array");

        recursiveAddKeys(ul,value);
    }else if(value.constructor === "".constructor){
        span = spanKey(li,key,'"'+value+'"');
        span.classList.add("primitive");
        li.classList.add("primitives");
    }else {
        span = spanKey(li,key,value);
        span.classList.add("primitive");
        li.classList.add("primitives");
    }
}
function spanKey(previous, key, value){
    var span = document.createElement('SPAN');
    previous.appendChild(span);
    span.innerText = key+': '+value;
    return span;
}
function generateLinkFromFile(){
    var file = document.getElementById('jsonFileInput');
    if(file.files.length>0){
        var reader = new FileReader();
        reader.addEventListener('load', function() {
            var result = JSON.parse(reader.result);
            document.getElementById('jsonTextInput').value = reader.result;
            fillTree(result);
        });
        reader.readAsText(file.files[0]);
    }
}
function generateLinkFromText(){
    var textBox = document.getElementById('jsonTextInput');
    try{
        fillTree(JSON.parse(textBox.value));
        console.log('JSON Tree Filled');
    } catch(err){
        console.log('JSON Tree Failed To Load');
        document.getElementById('JSONTree').innerHTML = err;
        console.error(err);
    }
}
function fillTree(json){
    var tree = document.getElementById('JSONTree');
    tree.innerHTML = '';
    try{
        recursiveAddKeys(tree,json);
        console.log('Tree Filled');
    } catch(err){
        console.log('JSON Tree Failed To Load');
        document.getElementById('JSONTree').innerHTML = err;
        console.error(err);
    }
    addClickListeners();
}
function addClickListeners(){
    var toggler = document.getElementsByClassName("caret");
    var i;
    for (i = 0; i < toggler.length; i++) {
        toggler[i].addEventListener("click", function() {
            this.parentElement.querySelector(".nested").classList.toggle("active");
            this.classList.toggle("caret-down");
        });
    }
}
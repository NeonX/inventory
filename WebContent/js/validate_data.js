	function isWhitespace(obj){
		//accept : spaces, tabs, etc.
		var reg = new RegExp(/^\s+$/);
		return (reg.test(obj.value) || obj.value == "");
	}
    
    function isInteger(obj){
    	var reg = new RegExp(/^\d+$/);
    	return reg.test(obj.value);
    }
    
    function isPhoneNumber(obj){
    	var reg = new RegExp(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/);
    	return reg.test(obj.value);
    }
    
    function checkPhoneNumber(obj){
    	if(!isWhitespace(obj)){
 			if(!isPhoneNumber(obj)){
 				if(obj.value != ""){
 					alert("กรุณาใส่หมายเลขโทรศัพท์ เช่น 081-222-3333 หรือ 0812223333");
 					obj.value = "";
 					obj.style.borderColor = "#ff0000";
 					obj.focus();
 					return false;
 				}
 			}
 		}else{
 			obj.value = "";
 		}
 		obj.style.borderColor = "";

 		return true;
	}
    
    function validateKmPattern(obj){
    	if(!isWhitespace(obj)){
 			if(!isKmNumber(obj)){
 				if(obj.value != ""){
 					alert("กรุณาใส่เลข กม. เช่น 10+000");
 					obj.value = "";
 					obj.style.borderColor = "#ff0000";
 					obj.focus();
 					return false;
 				}
 			}
 		}else{
 			obj.value = "";
 		}
 		obj.style.borderColor = "";

 		return true;
	}
    
    function isKmNumber(obj){
    	var reg = new RegExp(/\d+\+\d{3}$/);
    	return reg.test(obj.value);
    }
    
    function isNumber(obj){
    	//accept : integer , floating point
    	var reg = new RegExp(/^((\d+(\.\d*)?)|((\d*\.)?\d+))$/);
    	return reg.test(obj.value);
    }  
    
    function isNumberValue(value){
    	//accept : integer , floating point
    	var reg = new RegExp(/^((\d+(\.\d*)?)|((\d*\.)?\d+))$/);
    	return reg.test(value);
    }  
    
    function isDate(obj) {
		var reg = new RegExp( /^([01][1-9]|3[0-1])[/]([01][0-1]|1[0-2])[/](\d{4})$/ );
		return reg.test(obj.value);
	}
    
    function checkDate(obj){

 		if(!isWhitespace(obj)){
 			if(!isDate(obj)){
 				if(obj.value != ""){
 					alert("กรุณาเลือกวันที่");
 					obj.value = "";
 					obj.style.borderColor = "#ff0000";
 					obj.focus();
 					return false;
 				}
 			}
 		}else{
 			obj.value = "";
 		}
 		obj.style.borderColor = "";

 		return true;
 	}

    function checkNumber(obj){

 		if(!isWhitespace(obj)){
 			if(!isNumber(obj)){
 				if(obj.value != ""){
 					alert("กรุณากรอกเป็นตัวเลข");
 					obj.value = "";
 					obj.style.borderColor = "#ff0000";
 					obj.focus();
 					return false;
 				}
 			}
 		}else{
 			obj.value = "";
 		}
 		obj.style.borderColor = "";

 		return true;
 	}
    
     function checkInt(obj){
 		if(!isWhitespace(obj)){
 			if(!isInteger(obj)){
 				if(obj.value != ""){
 					alert("กรุณากรอกเป็นตัวเลขและไม่เป็นทศนิยม");
 					obj.value = "";
 					obj.style.borderColor = "#ff0000";
 					obj.focus();
 					return false;
 				}
 			}
 		}else{
 			obj.value = "";
 		}
 		obj.style.borderColor = "";

 		return true;	
 	}
     
    function checkCodeLength(obj, len){
    	//alert("isWhitespace : "+isWhitespace(obj));
    	if(!isWhitespace(obj)){
    		if(obj.value.length != len){
        		alert("กรุณากรอกให้ครบ "+len+" หลัก");
        		obj.style.borderColor = "#ff0000";
        		obj.focus();
        		return false;
        	}
    	}else{
    		obj.value = "";
    	}
    	
    	obj.style.borderColor = "";
    	return true;
    }
    
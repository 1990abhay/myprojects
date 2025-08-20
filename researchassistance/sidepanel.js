document.addEventListener("DOMContentLoaded",() => {
	chrome.storage.local.get(['researchNotes'], function(result){
		
		if(result.researchNotes){
			document.getElementById('notes').value=result.researchNotes.toString();
		}
		
	});
	
	document.getElementById('summarizebtn').addEventListener('click',summarizeText)
	document.getElementById('savebtn').addEventListener('click',saveNotes)
});

async function summarizeText(){
	try{
		const [tab] = await chrome.tabs.query({active:true,currentWindow:true});
		const [{result}]=await chrome.scripting.executeScript({
			target:{tabId:tab.id},
			function: () =>window.getSelection().toString()
		});
		if(!result){
			showResults("Please select some text first.")
			return;
		}
		
		const response=await fetch('http://localhost:8073/api/summaryassistance/processContent',{
			method:'POST',
			headers:{'content-Type':'application/json'},
			body:JSON.stringify({content:result,operation:'summarize'})
		});
		if(!response.ok){
			throw new Error('API Error : ${response.statrus}');
		}
		const text= await response.text();
		showResults(text.replace(/\n/g,'<br>'));
	}catch(error){
		showResults('ERROR : '+error.message);
	}
}
	
async function saveNotes(){
	const notes=document.getElementById('notes').value;
	chrome.storage.local.set({'researchNotes': notes}, function () {
		alert('Notes saved successfully');
	});
}

 function showResults(content){
	document.getElementById('results').innerHTML=`<div class="result-item"><div class="result-content">${content}</div></div>`;	
	//<div class="result-item"><div class="result-content">${content}</div></div>
}
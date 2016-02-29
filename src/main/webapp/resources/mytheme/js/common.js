
$(document).ready(function() {
	
	$(function() {
	    $( "#datepicker" ).datepicker({
	      changeMonth: true,
	      changeYear: true
	    });
	  });

	
	
	$("#header").click(function(event){
		$('#fakeform').submit()
	})	
	
	// Start of delete function
	
	
	var csrfToken = $("meta[name='_csrf']").attr("content"); 
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
	
	$('.del').click(function(event) {
	      //var producer = $('#producer').val();
	      var id 	= $(this).closest("tr").find(".empid").text();
	      var fname = $(this).closest("tr").find(".empfname").text();
	      var lname = $(this).closest("tr").find(".emplname").text();
	      var tel 	= $(this).closest("tr").find(".emptel").text();
	      //var json = { "id" : id, "fname" : fname, "lname": lname, "tel":tel};
	      var json = {"employeeId" : id}
	      console.log(JSON.stringify(json))
	    $.ajax({
	        url: 'deleteEmployee',
	        data: JSON.stringify(json),
	        data : json,
	        type: "GET",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(result) {
	            console.log("result = " + JSON.stringify(result));
	            output = JSON.stringify(result)
	            console.log("output = " + output);
	            if (result.statuscode == 0){
	            	console.log("fakeform submitted");
	            	$('#fakeform').submit()
	            }
	        }
	    });
	      
	    event.preventDefault();
	  });
	$('.upd').click(function(event) {
		var id 	= $(this).closest("tr").find(".empid").text();
		$('#fakeformupd :input').val(id);
		$('#fakeformupd').submit()
		
	})
	// Update function not used anymore but very good example for passing CSRF Spring
	/*$('.upd').click(function(event) {
	      //var producer = $('#producer').val();
	      var id 	= $(this).closest("tr").find(".empid").text();
	      var fname = $(this).closest("tr").find(".empfname").text();
	      var lname = $(this).closest("tr").find(".empfname").text();
	      var tel 	= $(this).closest("tr").find(".emptel").text();
	      var email	= $(this).closest("tr").find(".empemail").text();
	      var json = { "id" : id, "firstname" : fname, "lastname": fname, "telephone":tel, "email":email};
	      console.log(JSON.stringify(json));
	    $.ajax({
	        url: 'updateEmployee',
	        data: JSON.stringify(json),
	        type: "POST",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	            xhr.setRequestHeader(csrfHeader, csrfToken); //CSRF Token needs to be set
	        },
	        success: function(result) {
	            console.log("result = " + JSON.stringify(result));
	            output = JSON.stringify(result)
	            console.log("output = " + output);
	            if (result.statuscode == 0){
	            	console.log("fakeform submitted");
	            	$('#fakeform').submit()
	            }
	        }
	    });
	      
	    event.preventDefault();
	  });
*/
	}
)  

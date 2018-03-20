window.onload = function(){
	loadLanding();
}

function loadLanding(){
	var xhr= new XMLHttpRequest();
	xhr.open("GET", 'loadLanding.view', true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#login').on('click', login);
		}
	}
}

function postRF(user){
	var uu = user.username;
	var amount = $('#cost').val();
	var fid = $('#fil').val();
	var ty = 0; 
	 if($('#1').is(':checked')) { ty = 1; }
	 if($('#2').is(':checked')) { ty = 2; }
	 if($('#3').is(':checked')) { ty = 3; }
	 if($('#4').is(':checked')) { ty = 4; }
	 if($('#5').is(':checked')) { ty = 5; }
	 if($('#6').is(':checked')) { ty = 6; }
	 var notes = $('#exp').val();
	  var st = $('#ST').val() ;
	   var et = $('#ET').val();
	 var gf = $('#GF').val();
	 var send = [uu, amount, fid, ty, notes, st, et, gf];
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "NPT", true);
		xhr.send(JSON.stringify(send));
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){

					$('#message').show();
					$('#message').attr("style", "display:inline");
					var message ="Form Sent";
					$('#message').html(message);
					
			}
		}
}

function EReg(){
	var fn = $('#fn').val();
	var ln = $('#ln').val();
	var us = $('#username').val();
	var email = $('#email').val();
	var RT = $('#RT').val();
	var JobId = $('#JobId').val();
	var EMPID = $('#EMPID').val();
	var PWD = $('#PWD').val();
	var send = [us, fn, ln, email, RT, JobId, EMPID, PWD];
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "RegNE", true);
	console.log(xhr.open("POST", "RegNE", true));
	xhr.send(JSON.stringify(send));
	console.log("before");
	xhr.onreadystatechange = function(){
		console.log("before if");
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("after");
			var user = JSON.parse(xhr.responseText);
			var message = "";
			console.log(user);
			if(user == null){
				$('#message').show();
				$('#message').attr("style", "display:inline");
				message ="Employee is allready in the system.";
				$('#message').html(message);
				
			}
			else{
				$('#message').show();
				$('#message').attr("style", "display:inline");
				message ="Employee added";
				$('#message').html(message);
				
				
			}
		}
	}
}
function login(){
	var email = $('#email').val();
	var password = $('#pass').val();
	
	var toSend = [email, password];
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			var message = "";
			console.log(user);
			if(user== null){
				console.log("null stuff");
				$('#message').show();
				$('#message').attr("style", "display:inline");
				message ="Wrong username and or password";
				$('#message').html(message);
			}
			else{
				console.log("login");
				loadNav(user);
				loadHome(user);
			}
		}
	}
}

function backHome(user){
	loadNav(user);
	loadHome(user);
	console.log('done');
}

function loadHome(user){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(user.fname);
			$('#newEMP').html("");
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			//getUserAccounts();
		}
	}
}
function logout(){
	console.log("logging out");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout", true);
	xhr.send();
	$('#navbar').html('');
	$('#newEMP').html("");
	loadLanding();
}

function loadRF(user){
	loadNav(user);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "RF.view" , true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#newEMP').html(xhr.responseText);
			$('#view').html("");
			$('#register').click(function(){
				postRF(user);
			})
			
		}
	}
	
}
function loadPR(user){
	loadNav(user);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "PR.view" , true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#newEMP').html(xhr.responseText);
			$('#view').html("");
			var ii;
			for(ii = 0; user.pendingTransactions.length; ii++){
				$('#ta').append('<tr><th>' + user.pendingTransactions[ii].id +'</th>' + '<th>' + user.pendingTransactions[ii].startTime +'</th>' + '<th>' + user.pendingTransactions[ii].endTime +'</th>' + '<th>' + user.pendingTransactions[ii].amount +'</th></tr>')
			}
			$('#ap').hide();
			
		}
	}
}

function moveup(user){
	console.log("Hi from moveup")
	var xhr = new XMLHttpRequest();
	xhr.open("POST","NA",true)
	var toSend = [user.username, user.reportsto];
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var message = "";
				$('#message').show();
				$('#message').attr("style", "display:inline");
				message ="Has been approved";
				$('#message').html(message);
		}
	}
}
function loadNA(user){
	loadNav(user);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "NA.view" , true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#newEMP').html(xhr.responseText);
			$('#view').html("");
			var ii;
			for(ii = 0; user.na.length; ii++){
				$('#ta').append('<tr><th>' + user.na[ii].id +'</th>' + '<th>' + user.na[ii].startTime +'</th>' + '<th>' + user.na[ii].endTime +'</th>' + '<th>' + user.na[ii].amount +'</th></tr>');
			}
			console.log("before ap")
			$('#ap').on('click', function(){
				console.log("hi from ap");
				moveup(user);
			})
			
		}
	}
}
function loadNav(user){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			$('#NA').click(function(){
				loadNA(user);
			})
			$('#PR').click(function(){
				loadPR(user);
			})
			$('#RF').click(function(){
				loadRF(user);
			});
			$('#Home').click(function(){
			loadNav(user);
			loadHome(user);
			$('#newEMP').html("");});
			$('#logout').on('click',function(){
			logout();
			}
			);
			var temp = user.empId;
			console.log(temp.toString().startsWith('10'));
			if(temp.toString().startsWith('10')){
				$('#RNE').show();
				$('#RNE').on('click', function(){
					var te = new XMLHttpRequest();
					te.open("GET", "RNE.view", true);
					te.send();
					te.onreadystatechange = function(){
						if(te.readyState == 4 && te.status == 200){
							$('#newEMP').html(te.responseText);
							$('#view').html("");
							$('#register').on('click', EReg)
						}
					}
				});
			}
			else{
				$("#RNE").hide();
			}
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}

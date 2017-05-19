function changePassword() {
	/*swal({
		  title: 'Enter your password',
		  input: 'password',
		  inputAttributes: {
		    'maxlength': 10,
		    'autocapitalize': 'off',
		    'autocorrect': 'off'
		  }
		}).then(function (password) {
		  if (password) {
		    swal({
		      type: 'success',
		      html: 'Entered password: ' + password
		    })
		  }
		})*/
	swal({
		title: 'Change Password',
		html:
			'<form:form name="changePasswordForm" method="POST"><label>Current Password<input id="swal-input1" class="" type="password" aria-describedby="Enter Your Current Password" required=""></label>' +
			'<label>New Password<input id="swal-input2" class="" type="password" aria-describedby="Enter Your New Password" required=""></label>' +
			'<label>Confirm New Password<input id="swal-input3" class="" type="password" aria-describedby="Enter Your New Password" required=""></label></form:form>',
			preConfirm: function () {
				return new Promise(function (resolve) {
					resolve({
						oldPassword: $('#swal-input1').val(),
						newPassword1: $('#swal-input2').val(),
						newPassword2: $('#swal-input3').val()
					})
				})
			}
		}).then(function (result) {
			sendRequest(result);
		}).catch(swal.noop)
}

function sendRequest(data) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/profile/changePassword",
		data: JSON.stringify(data),
		success: function(data) {
			if(!data.okay) {
				swal(data.message, "", "error");
			}
			else {
				swal(data.message, "", "success");
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
			swal("Error connecting to server.", "Please refresh the page.", "error"); 
		}
	});
}

<%@ page language = "java" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Insurance Data Mining in CRM Systems</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="images/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
      <h1><img src="images/title.png" width="800" height="80" alt="Insurance Data Mining in CRM Systems" /></h1>
      <div id="booking">
          <h2>Form 1: Please enter your personal details</h2>
            <form action="PersonalInformation" method="post">
              <table summary="" cellspacing="0" cellpadding="0" border="0" align = "center">
                  <tr><th text-align="left">Name :</th><td>
                    <input name="name" type="text" value="" class="text" size="19" /> </td></tr>
                  <tr><th align="left">E-Mail Id :</th><td>
                    <input name="email" type="text" value="" class="text" size="20" /> </td></tr>

                  <tr><th align="left">Sex :</th><td>
                    <input name="sex" type="text" value="" class="text" size="20" /> </td></tr>
                  <tr><th align="left">Age :</th><td>
                    <input name="age" type="text" value="" class="text" size="20" /> </td></tr>
                  <tr><th align="left">Address :</th><td>
                    <input name="address" type="text" value="" class="text" size="20" /> </td></tr>
                  <tr><th align="left">City :</th><td>
                    <select size="" name="city" class="text">
                    <option value="0" selected>Chennai</option>
                    <option value="1">Coimbatore</option>
                    <option value="2">Dindigul</option>
                    <option value="3">Erode</option>
                    <option value="4">Kanchipuram</option>
                    <option value="5">Karur</option>
                    <option value="6">Kodaikanal</option>
                    <option value="7">Madurai</option>
                    <option value="8">Nagarcoil</option>
                    <option value="9">Namakkal</option>
                    <option value="10">Ooty</option>
                    <option value="11">Salem</option>
                    <option value="12">Tirunelveli</option>
                    <option value="13">Trichy</option>
                    <option value="14">Tuticorin</option>
                    <option value="15">Vellore</option>
                    <option value="16">Virudunagar</option>
                    <option value="17">Others</option>
                 </select> </td></tr>
                  <tr><th align="left">Occupation :</th><td>
                    <select name="occupation" class="text">
					<option value="0" selected>Accounts, Finance, Tax, CS, Audit</option>
					<option value="1">Teaching, Education</option>
					<option value="2">Farmer</option>
					<option value="3">Engineering</option>
					<option value="4">Sales<option>
					<option value="5">Healthcare, Medical, R&amp;D</option>
					<option value="6">Hotels, Restaurants</option>
					<option value="7">IT Software-Application Programming, Maintenance</option>
					<option value="8">Legal</option>
					<option value="9">Other</option></select></td></tr>
                  <tr><th align="left">Monthly Income :</th><td>
                    <select name="income" class="text">
					<option value="0">< 2,000</option>
					<option value="1">2,000 to 5,000</option>
					<option value="2">5,000 to 8,000</option>
					<option value="3">8,000 to 10,000</option>
					<option value="4">10,000 to 15,000<option>
					<option value="5">15,000 to 20,000</option>
					<option value="6">20,000<to 50,000/option>
					<option value="7">50,000 to 75,000</option>
					<option value="8">75,000 to 1,00,000/option>
					<option value="9">> 1,00,000</option>
					</select></td></tr>
                    <tr><th><input type="reset" value="Reset" class="submit" />  </th><td>
				<input type="Submit" value="Next" class="submit" /> </td></tr>

                    <!--input type="submit" value="submit" class="submit" /-->
              </table>
              </form>
             
      </div>  
      
      <img src="images/bottom.png" width="800" height="30"  />
      
</div>
</body>
</html>
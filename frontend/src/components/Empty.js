import { useState } from 'react';
import '../App.css';
function Empty() {

    const [firstName, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phoneNumber, setPhone] = useState("");
    const   [email, setEmail] = useState("");

    const handleSubmit = e => {
        e.preventDefault();
        var employee = {
            firstName,
            lastName,
            phoneNumber,
            email
        }
        fetch("http://localhost:8080/employees",
        {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(employee)
        })
        .then(props.newEmployee());
    }

    const handleNameChange = e => {
        setName(e.target.value);
    }

    const handleLastNameChange = e => {
        setLastName(e.target.value);
    }

    const handlePhoneChange = e => {
        setPhone(e.target.value);
    }

    const handleEmailChange = e => {
        setEmail(e.target.value);
    }

    return (
        <div className='cardContainer addNewCard'>
            <div className="cardStyle emptyCard">
                <form>
                    <p><input type="text" placeholder='Name' onChange={handleNameChange} value={firstName} /></p>
                    <p><input type="text" placeholder='Last Name' onChange={handleLastNameChange} value={lastName} /></p>
                    <p><input type="text" placeholder='Phone Number' onChange={handlePhoneChange} value={phoneNumber} /></p>
                    <p><input type="text" placeholder='Email' onChange={handleEmailChange} value={email} /></p>
                    <p><button onClick={handleSubmit} className='clickMe'>Add new Employee</button></p>
                </form>
            </div>
        </div>
      
    );
  }
  
  export default Empty;

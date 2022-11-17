import { useState } from 'react';
import '../App.css';
function Empty(props) {

    const [firstName, setName] = useState("");
    const [wrongName, setWrongName] = useState("");
    const [lastName, setLastName] = useState("");
    const [wrongLastName, setWrongLastName] = useState("");
    const [phoneNumber, setPhone] = useState("");
    const [wrongPhone, setWrongPhone] = useState("");
    const [email, setEmail] = useState("");
    const [wrongEmail, setWrongEmail] = useState("");

    const handleSubmit = e => {
        e.preventDefault();
        if(!validateData()) return;

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
        .then(() => {
            setName("");
            setLastName("");
            setPhone("");
            setEmail("");
        })
        .then(props.newEmployee());
    }

    const handleNameChange = e => {
        endValidation();
        setName(e.target.value);
    }

    const handleLastNameChange = e => {
        endValidation();
        setLastName(e.target.value);
    }

    const handlePhoneChange = e => {
        endValidation();
        setPhone(e.target.value);
    }

    const handleEmailChange = e => {
        endValidation();
        setEmail(e.target.value);
    }

    const validateData = () => {
        let ans = true;

        if(firstName.length < 2 || firstName.length > 25) {
            ans = false;
            setWrongName("wrong");
        }

        if(lastName.length < 2 || lastName.length > 25) {
            ans = false;
            setWrongLastName("wrong");
        }

        if(phoneNumber.length < 2 || phoneNumber.length > 20) {
            ans = false;
            setWrongPhone("wrong");
        }

        if(email.length > 25) {
            ans = false;
            setWrongEmail("wrong");
        }

        return ans;
    }

    const endValidation = () => {
        setWrongName("");
        setWrongLastName("");
        setWrongPhone("");
        setWrongEmail("");
    }

    return (
        <div className='cardContainer addNewCard'>
            <div className="cardStyle emptyCard">
                <form>
                    <p><input class={wrongName} type="text" placeholder='Name' onChange={handleNameChange} value={firstName} /></p>
                    <p><input class={wrongLastName} type="text" placeholder='Last Name' onChange={handleLastNameChange} value={lastName} /></p>
                    <p><input class={wrongPhone} type="text" placeholder='Phone Number' onChange={handlePhoneChange} value={phoneNumber} /></p>
                    <p><input class={wrongEmail} type="text" placeholder='Email' onChange={handleEmailChange} value={email} /></p>
                    <p><button onClick={handleSubmit} className='clickMe'>Add new Employee</button></p>
                </form>
            </div>
        </div>
      
    );
  }
  
  export default Empty;

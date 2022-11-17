import '../App.css';
function Card(props) {

    const deleteEmployee =() => {
        fetch('http://localhost:8080/employees/'+props.employee.id, { method: 'DELETE' })
        props.delete();
    }

    return (
        <div className='cardContainer'>
            <div className="cardStyle">
                <div className='moveRight clickMe' onClick={deleteEmployee}>X</div>
                <br></br>
                <p>{props.employee.firstName}</p>
                <p>{props.employee.lastName}</p>
                <p>{props.employee.phoneNumber}</p>
                <p>{props.employee.email}</p>
                <button className='clickMe'>Modify Card</button>
            </div>
        </div>
      
    );
  }
  
  export default Card;
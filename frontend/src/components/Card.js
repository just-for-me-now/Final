import '../App.css';
function Card(props) {
    return (
        <div className='cardContainer'>
            <div className="cardStyle">
                <button className='moveRight clickMe'>X</button>
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
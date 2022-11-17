import '../App.css';
function Card(props) {
    return (
        <div className='cardContainer'>
            <div className="cardStyle">
                <div className='moveRight clickMe'>X</div>
                <br></br>
                <p>{props.employee.firstName}</p>
                <p>{props.employee.lastName}</p>
                <p>{props.employee.phoneNumber}</p>
                <p>{props.employee.email}</p>
                <p className='clickMe'>Modify Card</p>
            </div>
        </div>
      
    );
  }
  
  export default Card;
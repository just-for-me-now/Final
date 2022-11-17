import Card from "./Card";
import Empty from "./Empty";
import '../App.css';
function Container(props) {

  const handleChange = () => {
    props.change();
  }

  return (
    <div className="cardsContainer">
      {props.data?.map(employee => <Card key={employee.id} delete={handleChange} employee={employee}/>)}
      <Empty newEmployee={handleChange}/>
    </div>
  );
}

export default Container;
import Card from "./Card";
import Empty from "./Empty";
import '../App.css';
function Container(props) {

  const handleChange = () => {
    props.change();
  }

  const handleModify = id => {
    props.modify(id);
  }

  return (
    <div className="cardsContainer">
      {props.data?.map(employee => <Card key={employee.id} id={employee.id} modify={handleModify} delete={handleChange} employee={employee}/>)}
      <Empty newEmployee={handleChange} buttonMsg={props.buttonMsg} />
    </div>
  );
}

export default Container;
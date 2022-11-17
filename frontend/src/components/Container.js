import Card from "./Card";
import Empty from "./Empty";
import '../App.css';
function Container(props) {

  return (
    <div className="cardsContainer">
      {props.data?.map(employee => <Card delete={props.change} employee={employee}/>)}
      <Empty newEmployee={props.change}/>
    </div>
  );
}

export default Container;
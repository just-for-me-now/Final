import Card from "./Card";
import Empty from "./Empty";
import '../App.css';
function Container(props) {

  const dataHandler = (e) => {
    e.preventDefault();
  }

  return (
    <div className="cardsContainer">
      {props.data?.map(employee => <Card employee={employee}/>)}
      <Empty/>
    </div>
  );
}

export default Container;
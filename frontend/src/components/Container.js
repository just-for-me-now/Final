import Card from "./Card";
import Empty from "./Empty";
import '../App.css';
function Container() {
  return (
    <div className="cardsContainer">
      <Card/>
      <Card/>
      <Card/>
      <Card/>
      <Card/>
      <Card/>
      <Card/>
      <Empty/>
    </div>
  );
}

export default Container;
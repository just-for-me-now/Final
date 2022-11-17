import './App.css';
import Container from './components/Container';
import CardInFront from './components/CardInFront';
import {useState, useEffect} from 'react'
function App() {

  const[data, setData] = useState();
  const [modifying, setModifying] = useState(null);

  const handleChange = useEffect(()=>{
    fetch('http://localhost:8080/employees')
    .then(response => response.json())
    .then(response => setData(response));
  }, [data]);

  const modify = id => {
    setModifying(id);
  }

  const handleReturnFromModify = () => {
    setModifying(null);
  }

  if(modifying != null) {
    return (
      <CardInFront id={modifying} returnFromModify={handleReturnFromModify} buttonMsg="modify" />
    );
  }



  return (
    <div className="App">
      <h1>Employee Finder</h1>
      <Container change={handleChange} data={data} modify={modify} buttonMsg="Add New Employee" />
    </div>
  );
}

export default App;

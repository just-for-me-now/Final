import './App.css';
import Container from './components/Container';
import {useState, useEffect} from 'react'
function App() {

  const[data, setData] = useState();

  const handleChange = useEffect(()=>{
    fetch('http://localhost:8080/employees')
    .then(response => response.json())
    .then(response => setData(response));
  }, [data]);

  return (
    <div className="App">
      <h1>Employee Finder</h1>
      <Container change={handleChange} data={data}/>
    </div>
  );
}

export default App;

import './App.css';
import Container from './components/Container';
import {useState, useEffect} from 'react'
function App() {

  const[data, setData] = useState();

  useEffect(()=>{
    fetch('http://localhost:8080/employees')
    .then(response => response.json())
    .then(response => setData(response));
  }, []);

  return (
    <div className="App">
      <h1>Employee Finder</h1>
      <Container data={data}/>
    </div>
  );
}

export default App;

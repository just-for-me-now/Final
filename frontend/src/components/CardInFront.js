import { useState, useEffect } from "react";
import Empty from "./Empty";

function CardInFront(props) {

    const [data, setData] = useState(null);

    useEffect(()=>{
        fetch('http://localhost:8080/employees/' + props.id)
        .then(response => response.json())
        .then(response => setData(response));
      }, []);

    return (
        <>
            {data !== null ? <Empty firstName={data.firstName} lastName={data.lastName} phoneNumber={data.phoneNumber} email={data.email} buttonMsg={props.buttonMsg} /> : ""}
        </>
        
    )
}

export default CardInFront;
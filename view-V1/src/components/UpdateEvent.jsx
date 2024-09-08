import React, { useEffect, useState } from 'react';
import { Button, Form } from 'semantic-ui-react'
import { useNavigate } from 'react-router-dom';
import LoadingBar from 'react-top-loading-bar'
import axios from 'axios';

export default function Update() {
  const [institutionName, setInstitutionName] = useState('');
  const [institutionType, setInstitutionType] = useState('');
  const [eventName, setEventName] = useState('');
  const [initialDate, setInitialDate] = useState('');
  const [finalDate, setFinalDate] = useState('');
  const [id, setID] = useState(null);
  const [progress, setProgress] = useState(0)

  let history = useNavigate();

  useEffect(() => {
    setID(localStorage.getItem('ID'))
    setInstitutionName(localStorage.getItem('institutionName'));
    setInstitutionType(localStorage.getItem('institutionType'));
    setEventName(localStorage.getItem('eventName'));
    setInitialDate(localStorage.getItem('initialDate'));
    setFinalDate(localStorage.getItem('finalDate'));

  }, []);

  const updateAPIData = () => {
    axios.put(`https://63b7b2474f17e3a931da1e08.mockapi.io/fakeData/${id}`, {
      institutionName,
      institutionType,
      eventName,
      initialDate,
      finalDate,
    }).then(() => {
      history('/read')
    })
  }

  const handleButtonClick = () => {

    setProgress(100); // Set the progress to 100
    updateAPIData();  // Call the postData function
  }

  return (
    <div>
      <h1>Update</h1>
      <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(100)}
      />
      <Form className="create-form">
        <Form.Field>
          <label>Nome da Instituição</label>
          <input
            placeholder='Nome da Instituição'
            onChange={(e) => setInstitutionName(e.target.value)}
          />
        </Form.Field>
        <Form.Field>
          <label>Tipo de Instituição</label>
          <input
            placeholder='Tipo de Instituição'
            onChange={(e) => setInstitutionType(e.target.value)}
          />
        </Form.Field>
        <Form.Field>
          <label>Nome do Evento</label>
          <input
            placeholder='Nome do Evento'
            onChange={(e) => setEventName(e.target.value)}
          />
        </Form.Field>
        <Form.Field>
          <label for="initial-date">Data de Inicio:</label>
          <input
            type="datetime-local"
            id="initial-date"
            name="initial-date"
            onChange={(e) => setInitialDate(e.target.value)}
          />
        </Form.Field>
        <Form.Field>
          <label for="final-date">Data de Finalização:</label>
          <input
            type="datetime-local"
            id="final-date"
            name="final-date"
            onChange={(e) => setFinalDate(e.target.value)}
          />
        </Form.Field>
        <Button
          type='submit'
          onClick={handleButtonClick}
        >
          Submit
        </Button>
      </Form>
    </div>
  )
}
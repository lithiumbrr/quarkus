import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {Button, Form} from 'semantic-ui-react';
import LoadingBar from 'react-top-loading-bar';
import axios from 'axios';

export default function Create() {
    const [institutionName, setInstitutionName] = useState('');
    const [institutionType, setInstitutionType] = useState('');
    const [eventName, setEventName] = useState('');
    const [initialDate, setInitialDate] = useState('');
    const [finalDate, setFinalDate] = useState('');

    const [progress, setProgress] = useState(0)
    let history = useNavigate();

    const postData = () => {
        axios.post('https://63b7b2474f17e3a931da1e08.mockapi.io/fakeData', {
            institutionName, institutionType, eventName, initialDate, finalDate,
        }).then(() => {
            history('/read');
        })
    }

    const handleButtonClick = () => {
        if (!validateDate()) {
            return;
        }
        postData();   // Call the postData function
        setProgress(100); // Set the progress to 100
    }

    const validateDate = () => {
        const currentDate = new Date();
        const startDate = new Date(initialDate);
        const endDate = new Date(finalDate);

        if (startDate < currentDate) {
            alert("A data de início não pode ser anterior à data atual. Por favor Verifique o campo de Data Inicial");
            return false;
        }

        if (endDate < startDate) {
            alert("A data de final do evento não pode ser anterior à data de início. Por favor Verifique o campo de Data Final");
            return false;
        }

        return true;

    }

    return (<div>
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
    </div>)
}
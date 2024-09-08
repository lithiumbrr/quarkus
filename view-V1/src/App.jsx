import {BrowserRouter as Router, Route, Routes, Link, Navigate} from 'react-router-dom';
import Create from './components/Create';
import Read from './components/Read';
import Update from './components/Updated';
import UpdateEvent from './components/UpdateEvent';
import UpdateInstitution from './components/UpdateInstitution';
import CreateEvent from './components/CreateEvent';
import CreateInstitution from './components/CreateInstitution';

import './App.css';
import {Header, Menu} from "semantic-ui-react";

function App() {
    return (
        <Router>
            <div className="main">
                <Menu>
                    <Menu.Item as={Link} to="/create">Create</Menu.Item>
                    <Menu.Item as={Link} to="/read">Read</Menu.Item>
                    <Menu.Item as={Link} to="/update">Update</Menu.Item>
                    <Menu.Item as={Link} to="/createEvent">Create Event</Menu.Item>
                    <Menu.Item as={Link} to="/updateEvent">Update Event</Menu.Item>
                    <Menu.Item as={Link} to="/createInstitution">Create Institution</Menu.Item>
                    <Menu.Item as={Link} to="/updateInstitution">Update Institution</Menu.Item>
                </Menu>
                <div className="content">
                    <Header as="h2" className="main-header" style={{ color: 'white' }}>
                        CRUD Event Form
                    </Header>
                    <Routes>
                        <Route exact path='/' element={<Navigate to="/create"/>}/>
                        <Route path='/create' element={<Create/>}/>
                        <Route path='/read' element={<Read/>}/>
                        <Route path='/update' element={<Update/>}/>
                        <Route path='/createEvent' element={<CreateEvent/>}/>
                        <Route path='/updateInstitution' element={<UpdateInstitution/>}/>
                        <Route path='/updateEvent' element={<UpdateEvent/>}/>
                        <Route path='/createInstitution' element={<CreateInstitution/>}/>
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
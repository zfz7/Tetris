import React from 'react'
import './App.css'
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import {HelloWorld} from "./components/HelloWorld";

export const App: React.FC = () => <BrowserRouter>
        <Switch>
          <Route path="/">
            <HelloWorld/>
          </Route>
        </Switch>
</BrowserRouter>

export default App
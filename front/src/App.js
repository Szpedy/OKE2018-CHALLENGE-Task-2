import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import TextForm from './TextForm';

class App extends Component {

    state = {};

    componentDidMount() {
        setInterval(this.hello, 250);
    }

    hello = () => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <TextForm />
                    <h1 className="App-title">{this.state.message}</h1>
                </header>
               
            </div>
        );
    }
}

export default App;

import React, { Component } from 'react';
class TextForm extends Component {
    handleSubmit = (e) => {
        e.preventDefault();
        const message = this.getMessage.value;
        const data = {
            message
        }
        fetch('/api/result', { method: 'POST', body: message })
            .then(response => response.text())
            .then(message => {
                console.log(message);
                this.getResult.value = message;
            });
        this.getMessage.value = '';
    }
    render() {
        return (
            <div className="post-container">
                <h1 className="post_heading">Input Text</h1>
                <form className="form" onSubmit={this.handleSubmit} >
                    <textarea required rows="5" ref={(input) => this.getMessage = input}
                        cols="28" placeholder="Text to Identify" /><br />
                    <button>Identify</button>
                <h1 className="post_heading">Result</h1>
                    <textarea rows="10" ref={(input) => this.getResult = input}
                        cols="28" placeholder="Result" />
                </form>
            </div>
        );
    }
}
export default TextForm;
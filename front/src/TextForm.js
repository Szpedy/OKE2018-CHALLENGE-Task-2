
import React, { Component } from 'react';
import axios from 'axios';

class TextForm extends Component {
    handleSubmit = async (e) => {
        e.preventDefault();
        const message = this.getMessage.value;
        const data = {
            text: message
        }
        const res = await axios.post('/api/result', { text: message }, {
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
        })
        this.getResult.value = res.data;
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
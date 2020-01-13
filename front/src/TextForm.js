
import React, { Component } from 'react';
import axios from 'axios';
import ClipLoader from "react-spinners/ClipLoader";

class TextForm extends Component {
    constructor() {
        super();
        this.state = {
            resultMap: '',
            text: '',
            loading:false
        }
    }
    handleSubmit = async (e) => {
        e.preventDefault();
        const message = this.getMessage.value;
        const data = {
            text: message
        }
        this.state.loading = true;
        const res = await axios.post('/api/result', { text: message }, {
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
        })
        console.log(res);
        console.log('res.data');
        console.log(res.data);
        this.state.resultMap = res.data;
        // this.getMessage.value = '';
        this.createText();
    }

    createText = () => {
        let splited = this.getMessage.value.split(" ");
        console.log('splited');
        console.log(splited);
        console.log('this.state.resultMap');
        console.log(this.state.resultMap);
        // Outer loop to create parent
        //Inner loop to create children
        for (let i = 0; i < splited.length; i++) {
            if (splited[i] in this.state.resultMap) {
                console.log('splited[i]');
                console.log(splited[i]);
                console.log('this.state.resultMap[splited[i]]');
                console.log(this.state.resultMap[splited[i]]);
                this.state.text += `<mark data-entity=\"${this.state.resultMap[splited[i]].toLowerCase()}\">${splited[i]}</mark> `
            }
            this.state.text += `${splited[i]} `
        }
        console.log('this.state.text')
        console.log(this.state.text)
        this.state.loading = false;
        return this.state.text
    }
    render() {
        return (
            <div className="post-container">
                <h1 className="post_heading">Input Text</h1>
                <form className="form" onSubmit={this.handleSubmit} >
                    <textarea required rows="5" ref={(input) => this.getMessage = input}
                        cols="28" placeholder="Text to Identify" /><br />
                    <button>Identify</button>
                    {/* <h1 className="post_heading">Result</h1>
                    <textarea rows="10" ref={(input) => this.getResult = input}
                        cols="28" placeholder="Result" /> */}
                    <ClipLoader
                        size={150}
                        //size={"150px"} this also works
                        color={"#123abc"}
                        loading={this.state.loading}
                    />
                    <div style={{ color: "black" }} class="entities" dangerouslySetInnerHTML={{ __html: this.state.text }}>

                    </div>
                </form>
            </div>
        );
    }
}
export default TextForm;
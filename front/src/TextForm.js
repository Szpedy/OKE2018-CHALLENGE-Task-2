
import React, { Component } from 'react';
import axios from 'axios';
import ClipLoader from "react-spinners/ClipLoader";

class TextForm extends Component {
    constructor() {
        super();
        this.state = {
            resultMap: '',
            text: '',
            loading: false
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
                'Content-Type': 'application/json',
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
        this.state.text = "";
        // Outer loop to create parent
        //Inner loop to create children
        for (let i = 0; i < this.state.resultMap.length; i++) {
            let entityData = this.state.resultMap[i];
            try {
                var href = entityData.dbpediaMapping[0]["Resource Uri"];
            } catch (error) {
                href = undefined;
            }
            console.log(href);
            console.log(entityData);
            if (href) {
                this.state.text += `<mark data-entity=\"${entityData.entityType.toLowerCase()}\"><a href="${href}">${entityData.entity}</a></mark> `
            } else{
            this.state.text += `<mark data-entity=\"${entityData.entityType.toLowerCase()}\">${entityData.entity}</mark> `
            }
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
                    <br />
                    <button>Identify</button>
                    <br />
                    <br />
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
import React, { Component } from 'react';
import LoadingProgress from './LoadingProgress';

class Diff extends Component {

  constructor(props) {
    super(props);

    this.handleSubmit = this.handleSubmit.bind(this);
    this.fileInput1 = React.createRef();
    this.fileInput2 = React.createRef();

    this.state = {
      diffs: null,
      error: null,
      isLoaded: true
    };
  }

  handleSubmit(event) {
    event.preventDefault();
    this.setState({ isLoaded: false, diffs: null });
    this.uploadFile(this.fileInput1.current.files[0], this.fileInput2.current.files[0]);
  }

  uploadFile(file1, file2) {
    var formData = new FormData();

    formData.append(`file1`, file1);
    formData.append(`file2`, file2);

    fetch('/api/v1/upload', {
      method: 'POST',
      body: formData,
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error(response.status + ' - Something went wrong ...');
        }
      })
      .then(data => {
        this.setState({ diffs: data, isLoaded: true });
      })
      .catch(error => this.setState({ error, isLoaded: true })
    );
  }

  render() {
    const { isLoaded, error, diffs } = this.state;

    if (error) {
      return (
        <b>{error.message}</b>
      );
    } else {
      return (
        <div>
          <h2>Diff</h2>

          <form onSubmit={this.handleSubmit}>
            <label>
              File 1:
              <input type="file" ref={this.fileInput1} />
            </label>
            <br />
            <label>
              File 2:
              <input type="file" ref={this.fileInput2} />
            </label>
            <br />
            <button type="submit">Diff</button>
          </form>

          {!isLoaded &&
            <LoadingProgress />
          }

          {diffs &&
            <h3>Result:</h3>
          }
          {diffs && diffs.map((data, key) => {
            var color = 'white';
            var textDecoration = 'none';
            if (data.type == '-') {
              color = 'red';
              textDecoration = 'underline';
            } else if (data.type == '+') {
              color = 'green';
              textDecoration = 'underline';
            }
            return (
              <span style={{color: color, textDecoration: textDecoration}} key={key}>
                {data.string}
              </span>
            );
          })}

        </div>
      );
    }
  }
}

export default Diff;

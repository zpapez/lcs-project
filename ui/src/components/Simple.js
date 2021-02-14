import React, { Component } from 'react';
import LoadingProgress from './LoadingProgress';


class Simple extends Component {

  constructor(props) {
    super(props);

    this.state = {
      hits: null,
      error: null,
      isLoaded: false
    };
  }

  componentDidMount() {
    this.setState({
        isLoaded: false
    });
    this.getDataFromBackend();
  }

  getDataFromBackend() {
      fetch('/api/v1/diff')
          .then(this.fetchStatusHandler)
          .then((json) => {

              this.setState({
                  isLoaded: true,
                  hits: json
              });
          },
              (error) => {
                  this.setState({
                      isLoaded: true,
                      error
                  });
              }
          );
  }

  fetchStatusHandler = (response) => {
      if (response.status === 200) {
          return response.json();
      } else {
          throw new Error('fetching all runs from backend - status ' + response.status + ' - ' + response.statusText);
      }
  }


  render() {
    const { isLoaded, error, hits } = this.state;

    if (hits != null) {
      console.log(hits);
    }

    if (error) {
      return (
        <b>{error.message}</b>
      );
    } else if (!isLoaded) {
      // Loading - centralized
      return <LoadingProgress />;
    } else {
      return (
        <div>
          <h2>Diff</h2>

          <h3>Result:</h3>
          <div className="stock-container">
            {hits.map((data, key) => {
              return (
                <span style={{color:'red'}} key={key}>
                  {data.removed}
                </span>
              );
            })}
          </div>

        </div>
      );
    }
  }
}

export default Simple;

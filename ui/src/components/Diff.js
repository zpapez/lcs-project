import React, { Component } from 'react';
import LoadingProgress from './LoadingProgress';

class Diff extends Component {

  constructor(props) {
    super(props);

    this.state = {
      hits: null,
      error: null,
      isLoaded: false
    };
  }

  componentDidMount() {
    fetch('/api/v1/diff')
    .then(response => {
      if (response.ok) {
        return response.text();
      } else {
        throw new Error(response.status + ' - Something went wrong ...');
      }
    })
    .then(data => this.setState({ hits: data, isLoaded: true }))
    .catch(error => this.setState({ error, isLoaded: true }));
  }

  render() {
    const { isLoaded, error, hits } = this.state;

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
          <h2>Data:</h2>
          <p>
            {hits}
          </p>
        </div>
      );
    }
  }
}

export default Diff;

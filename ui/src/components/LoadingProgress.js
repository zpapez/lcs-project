import React from 'react';

import { Typography, CircularProgress } from '@material-ui/core'

const LoadingProgress = (props) => {

    return (
        <div style={{ position: 'absolute', left: '50%', top: '50%', transform: 'translate(-50%, -50%)' }}>
            <center>
                <CircularProgress status="loading" />
                <Typography>Loading data...</Typography>
            </center>
        </div>
    );
}

export default LoadingProgress;

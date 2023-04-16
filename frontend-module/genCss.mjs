import { extractStyle } from '@ant-design/static-style-extract';
import { ConfigProvider, theme } from 'antd';

import fs from 'fs';
import React from 'react';

const outputPath = './styles/antd.min.css';

const cssText = extractStyle((node) => {
  return React.createElement(
    ConfigProvider,
    {
      theme: { algorithm: theme.darkAlgorithm, hashed: false }
    },
    node
  );
});

fs.writeFileSync(outputPath, cssText);

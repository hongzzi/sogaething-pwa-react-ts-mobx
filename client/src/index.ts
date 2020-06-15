import bodyParser from 'body-parser'
import cookieParser from 'cookie-parser'
import cors from 'cors';
import express from 'express'
import asyncify from 'express-asyncify'
import session from 'express-session'
import next from 'next'
import path from 'path'
import api from './api'
import conf from './next.config'
import { NEXT_APP_STAGE } from './services/index/helpers/config';

const dev = NEXT_APP_STAGE !== 'production'
const port = dev ? 3000 : 80

main()

async function main() {
  const app = next({
    conf,
    dev,
    dir: path.resolve(__dirname, './'),
  })

  await app.prepare()

  const server = asyncify(express())
  server.get('/service-worker.js', (req, res) => {
    const filePath = path.join(__dirname, 'dist', 'service-worker.js')
    app.serveStatic(req, res, filePath)
  })
  server.use(cors({
    credentials: true,
  }))
  server.use(bodyParser.json({limit: '50mb'}))
  server.use(bodyParser.urlencoded({ extended: true }))
  server.use(cookieParser())

  server.use(session({
    secret: '602f0a56-136b-524b-9871-d0450ef60b48',
    resave: true,
    saveUninitialized: true,
  }))

  server.use(api)
  server.use((req: any, res) => {
    return app.render(req, res, req._parsedUrl.pathname, req.query)
  })

  server.listen(port)
}
